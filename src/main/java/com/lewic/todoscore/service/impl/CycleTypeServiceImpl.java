package com.lewic.todoscore.service.impl;

import com.lewic.todoscore.common.Enums;
import com.lewic.todoscore.dao.jpa.primary.CycleTypeDao;
import com.lewic.todoscore.dao.jpa.primary.TaskDao;
import com.lewic.todoscore.dao.jpa.primary.WorkdayDao;
import com.lewic.todoscore.entity.jpa.primary.CycleType;
import com.lewic.todoscore.entity.jpa.primary.Workday;
import com.lewic.todoscore.service.CycleTypeService;
import com.lewic.todoscore.utils.CronUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CycleTypeServiceImpl(CycleTypeDao cycleTypeDao, WorkdayDao workdayDao) {
        this.cycleTypeDao = cycleTypeDao;
        this.workdayDao = workdayDao;
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
    public List<CycleType> listCycleType() {
        return cycleTypeDao.findAll();
    }

    @Override
    public void insertOne(CycleType cycleType) {
        cycleTypeDao.save(cycleType);
    }

    @Override
    public void deleteOne(Integer id) {
        cycleTypeDao.deleteById(id);
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

    // 通过工作日过滤
    private Boolean filterByWorkday(CycleType cycleType, Date date) throws Exception {
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
                if (workday.getDay().equals(date)) {
                    result = workday.getIsWorkday();
                }
            }
            if (result != null) {
                result = CronUtil.isWeekend(date);
            }
        } else if (Enums.WorkdayStatus.SKIP_WORKDAY.getCode().equals(workdayStatus)) {
            // 跳过工作日时，先判断特殊的workday里是否包含,包含则取workday的isWorkday相反值，不包含则判断是否为周六周日
            for (Workday workday : workdays) {
                if (workday.getDay().equals(date)) {
                    result = !workday.getIsWorkday();
                }
            }
            if (result != null) {
                result = !CronUtil.isWeekend(date);
            }
        }
        log.debug("today is satisfied workday condition: {}", result);
        return result;
    }

}
