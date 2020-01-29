package com.lewic.todoscore.scheduler;

import com.lewic.todoscore.common.Constants;
import com.lewic.todoscore.service.impl.TaskRecordServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class ImportRecordScheduler {

    private final TaskRecordServiceImpl taskRecordService;

    @Autowired
    public ImportRecordScheduler(TaskRecordServiceImpl taskRecordService) {
        this.taskRecordService = taskRecordService;
    }

    /**
     * 默认是fixedDelay 上一次执行完毕时间后执行下一轮
     */
    @Scheduled(cron = Constants.CRON_EVERY_DAY)
    public void importRecord() throws Exception {
//        Thread.sleep(6000);
        taskRecordService.insertToday();
        System.out.println(Thread.currentThread().getName() + "=====>>>>>使用cron  {}" + (System.currentTimeMillis() / 1000));
    }

    /**
     * fixedRate:上一次开始执行时间点之后5秒再执行
     */
    @Scheduled(initialDelay = 100 * 1000, fixedRate = 100 * 1000)
    public void test1() throws Exception {
//        try {
//        taskRecordService.insertToday();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        log.info("exec test1=====>>>>>");
    }

//    /**fixedDelay:上一次执行完毕时间点之后5秒再执行*/
//    @Scheduled(fixedDelay = 5000)
//    public void run2() throws InterruptedException {
//        Thread.sleep(7000);
//        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用fixedDelay  {}"+(System.currentTimeMillis()/1000));
//    }
//
//    /**第一次延迟2秒后执行，之后按fixedDelay的规则每5秒执行一次*/
//    @Scheduled(initialDelay = 2000, fixedDelay = 5000)
//    public void run3(){
//        System.out.println(Thread.currentThread().getName()+"=====>>>>>使用initialDelay  {}"+(System.currentTimeMillis()/1000));
//    }
}

