package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.common.Enums;
import com.lewic.todoscore.dao.CycleTypeDao;
import com.lewic.todoscore.dao.WorkdayDao;
import com.lewic.todoscore.entity.CycleType;
import com.lewic.todoscore.entity.Workday;
import com.lewic.todoscore.service.CycleTypeService;
import com.lewic.todoscore.utils.CronUtil;
import com.lewic.todoscore.utils.DateUtil;
import com.lewic.todoscore.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lewic
 * @since 2020/1/19 0:58
 */
@Slf4j
@Service
public class CycleTypeServiceImpl implements CycleTypeService {

    private final CycleTypeDao cycleTypeDao;

    private final WorkdayDao workdayDao;

    private final RedisUtil redisUtil;

    @Autowired
    public CycleTypeServiceImpl(CycleTypeDao cycleTypeDao, WorkdayDao workdayDao, RedisUtil redisUtil) {
        this.cycleTypeDao = cycleTypeDao;
        this.workdayDao = workdayDao;
        this.redisUtil = redisUtil;
    }

    @Override
    public Integer[] listCycleTypeIdByToday() throws Exception {
        // todo 后期可以换成findByUser
        List<CycleType> cycleTypes = cycleTypeDao.findAll();
        Date today = new Date();
        List<CycleType> todayCycleTypes = new ArrayList<>();

        for (CycleType ct : cycleTypes) {
            if (filterByCronAndWorkday(ct, today)) {
                todayCycleTypes.add(ct);
            }
        }
        Integer[] cycleTypeIds = new Integer[todayCycleTypes.size()];
        for (int i = 0; i < todayCycleTypes.size(); i++) {
            cycleTypeIds[i] = todayCycleTypes.get(i).getId();
        }

        return cycleTypeIds;

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CycleType> listCycleType() {
        // TODO 添加缓存,id假设为0,用户id需要补上
        int id = 0;
        String key = "user_" + id;
        if (redisUtil.hasKey(key)) {
            return (List<CycleType>) redisUtil.get(key);
        }

        List<CycleType> cycleTypes = cycleTypeDao.findAll();
        // 写入缓存, todo 可以考虑异步写入
        redisUtil.lSet(key, cycleTypes, Constants.DEFAULT_CACHE_TMOUT_TIME);
        return cycleTypes;

    }

    @Override
    public void insertOne(CycleType cycleType) {
        cycleType.setId(null);
        cycleTypeDao.save(cycleType);

        // 插入后需更新缓存
        int id = 0;
        String key = "user_" + id;
        redisUtil.lSet(key, cycleType, Constants.DEFAULT_CACHE_TMOUT_TIME);
    }

    @Override
    public void deleteOne(Integer id) {
        cycleTypeDao.deleteById(id);
        int userId = 0;
        String key = "user_" + userId;
        redisUtil.del(key);
    }

    // 通过循环类型指定的规则过滤
    @Override
    public Boolean filterByCronAndWorkday(CycleType cycleType, Date date) throws Exception {
        if (CronUtil.checkCronWithTime(cycleType.getCronExpression(), date) && filterByWorkday(cycleType, date)) {
            log.debug("{} is satisfied today,cycleTypeId is {}", cycleType.getCronExpression(), cycleType.getId());
            return true;
        } else {
            log.debug("{} is not satisfied today,cycleTypeId is {}", cycleType.getCronExpression(), cycleType.getId());
            return false;
        }
    }

    /**
     * 通过工作日过滤
     *
     * @param cycleType 循环方式
     * @param date      指定日期
     * @return true:指定日期满足; false:指定日期不满足
     * @throws Exception 异常
     */
    private Boolean filterByWorkday(CycleType cycleType, Date date) throws Exception {
        Date dateStart = DateUtil.getDayStart(date);
        Boolean result = null;
        List<Workday> workdays = workdayDao.findAll();
        if (workdays.isEmpty()) {
            log.warn("get workday table database is null");
            return true;
        }
        Integer workdayStatus = cycleType.getWorkdayStatus();
        if (Enums.WorkdayStatus.NOT_SET.getCode().equals(workdayStatus)) {
            log.debug(String.format("%d status is: %s", cycleType.getId(), Enums.WorkdayStatus.NOT_SET.getValue()));
            return true;
        } else if (Enums.WorkdayStatus.SKIP_HOLIDAY.getCode().equals(workdayStatus)) {
            // 跳过节假日时，先判断特殊的workday里是否包含,包含则取workday的isWorkday值，不包含则判断是否为周六周日
            for (Workday workday : workdays) {
                if (workday.getDay().equals(dateStart)) {
                    result = workday.getIsWorkday();
                }
            }
            if (result == null) {
                result = !CronUtil.isWeekend(date);
            }
        } else if (Enums.WorkdayStatus.SKIP_WORKDAY.getCode().equals(workdayStatus)) {
            // 跳过工作日时，先判断特殊的workday里是否包含,包含则取workday的isWorkday相反值
            for (Workday workday : workdays) {
                if (workday.getDay().equals(dateStart)) {
                    result = !workday.getIsWorkday();
                }
            }
            // dateStart不在workday列表,则如果为周六周日也满足
            if (result == null) {
                result = CronUtil.isWeekend(date);
            }
        }
        log.debug("today is satisfied workday condition: {}", result);
        return result;
    }

}
