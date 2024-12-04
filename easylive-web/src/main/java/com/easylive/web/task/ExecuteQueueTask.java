package com.easylive.web.task;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constants.Constants;
import com.easylive.entity.po.VideoInfoFilePost;
import com.easylive.redis.RedisUtils;
import com.easylive.service.VideoInfoPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class ExecuteQueueTask {
    private ExecutorService executorService = Executors.newFixedThreadPool(Constants.LENGTH_2);

    @Resource
    private RedisComponent redisComponent;

    @Resource
    private VideoInfoPostService videoInfoPostService;

    @PostConstruct
    public void consumTrasnferFIleQueue() {
        executorService.execute(() -> {
            while (true) {
                try {
                    VideoInfoFilePost videoInfoFile = redisComponent.getFileFromTransferQueue();
                    if (videoInfoFile == null) {
                        Thread.sleep(1500);
                        continue;
                    }
                    videoInfoPostService.transferVideoFile(videoInfoFile);
                } catch (Exception e) {
                    log.error("获取转码文件队列信息失败", e);
                }
            }
        });
    }

//    @PostConstruct
//    public void consumQueue() {
//        executorService.execute(()->{
//            while (true) {
//                try {
//                    VideoInfoFilePost videoInfoFile = redisComponent.getFileFromTransferQueue();
//                    if (videoInfoFile==null){
//                        Thread.sleep(1500);
//                        continue;
//                    }
//                    videoInfoPostService.transferVideoFile(videoInfoFile);
//                }catch (Exception e) {
//                    log.error("获取转码文件队列信息失败",e);
//                }
//            }
//        });
//    }
}