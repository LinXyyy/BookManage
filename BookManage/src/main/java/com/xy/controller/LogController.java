package com.xy.controller;

import com.xy.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author x1yyy
 */
@Controller
public class LogController {

    @Autowired
    LogService logService;

    @ResponseBody
    @RequestMapping("/getLog")
    public Map<String, Object> getLog() {
        return logService.getLog();
    }
}
