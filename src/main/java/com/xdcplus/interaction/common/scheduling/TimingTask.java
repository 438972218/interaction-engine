package com.xdcplus.interaction.common.scheduling;

import com.xdcplus.interaction.service.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @author Rong.Jia
 * @date 2021/5/31 13:11
 */
@Slf4j
@Component
@Profile("pro")
public class TimingTask {

    @Autowired
    private HttpService httpService;

    @Autowired
    private TaskExecutor taskExecutor;

    /**
     * 刷新部门缓存信息
     * 定时：每10分钟刷新一次
     */
    @Scheduled(fixedDelay = 1000 * 60 * 10)
    public void refreshDepartmentsCache() {

        log.info("refreshDepartmentsCache {}", System.currentTimeMillis());

        taskExecutor.execute(() -> httpService.refreshDepartmentsCache());

    }




}
