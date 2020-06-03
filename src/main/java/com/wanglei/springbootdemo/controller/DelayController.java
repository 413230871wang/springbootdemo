package com.wanglei.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.wanglei.springbootdemo.redis.delayredis.DelayJob;
import com.wanglei.springbootdemo.redis.delayredis.Job;
import com.wanglei.springbootdemo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delay")
public class DelayController {
    @Autowired
    private JobService jobService;
    /**
     * 添加
     * @param request
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String addDefJob(Job request) {
        DelayJob delayJob = jobService.addDefJob(request);
        return JSON.toJSONString(delayJob);
    }

    /**
     * 获取
     * @return
     */
    @RequestMapping(value = "pop",method = RequestMethod.GET)
    public String getProcessJob(String topic) {
        Job process = jobService.getProcessJob(topic);
        return JSON.toJSONString(process);
    }

    /**
     * 完成一个执行的任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "finish",method = RequestMethod.DELETE)
    public String finishJob(Long jobId) {
        jobService.finishJob(jobId);
        return "success";
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public String deleteJob(Long jobId) {
        jobService.deleteJob(jobId);
        return "success";
    }
}
