package com.xy.service.impl;

import com.xy.dao.LogMapper;
import com.xy.pojo.Log;
import com.xy.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author x1yyy
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public Map<String, Object> getLog() {
        List<Log> logs = logMapper.getLog();
        Map<String, Object> results = new HashMap<>();
        results.put("resultCode", 0);
        results.put("resultMessage", "success");
        results.put("data", logs);

        return results;
    }
}
