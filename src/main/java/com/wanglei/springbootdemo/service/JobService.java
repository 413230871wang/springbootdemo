package com.wanglei.springbootdemo.service;

import com.wanglei.springbootdemo.redis.delayredis.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private DelayBucket delayBucket;
    @Autowired
    private JobPool jobPool;
    @Autowired
    private ReadyQueue readyQueue;


    public DelayJob addDefJob(Job request) {
        DelayJob delayJob = new DelayJob(request);
        delayBucket.addDelayJob(delayJob);
        return delayJob;
    }

    public Job getProcessJob(String topic) {
        DelayJob delayJob = readyQueue.popJob(topic);
        Long jodId= delayJob.getJodId();
        Job job = jobPool.getJob(jodId);
        return job;
    }

    public void finishJob(Long jobId) {
        jobPool.removeDelayJob(jobId);
    }

    public void deleteJob(Long jobId) {
        jobPool.removeDelayJob(jobId);
    }
}
