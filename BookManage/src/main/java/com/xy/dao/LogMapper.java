package com.xy.dao;

import com.xy.pojo.Log;

import java.util.List;

/**
 * @author x1yyy
 */
public interface LogMapper {
    /**
     * 获得日志
     * @return s
     */
    List<Log> getLog();

    /**
     * 添加日志
     * @param log 日志
     */
    void addLog(Log log);
}
