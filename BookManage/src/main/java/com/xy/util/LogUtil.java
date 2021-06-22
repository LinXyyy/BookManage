package com.xy.util;

import com.mchange.v1.util.ArrayUtils;
import com.xy.dao.BookMapper;
import com.xy.dao.LogMapper;
import com.xy.pojo.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author x1yyy
 */
@Component
public class LogUtil {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    LogMapper logMapper;

    public Object getReturn(JoinPoint joinPoint, Map<String, Object> rvt) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String[] parameters =  methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        switch (methodName) {
            case "addBook":
                methodName = "添加图书";
                break;
            case "updateBook":
                methodName = "更新图书";
                break;
            case "deleteBook":
                methodName = "删除图书";
                break;
            case "borrowBook":
                methodName = "借阅图书";
                break;
            case "stillBook":
                methodName = "归还图书";
                break;
            default:
        }

        if ((int) rvt.get("resultCode") == 0) {
            Map<String, Object> params = new HashMap<>();
            String userName;
            Date date = new Date();
            String bookName;

            for (int i = 0; i < parameters.length; i++) {
                params.put(parameters[i], args[i]);
            }

            if (params.get("account") == null) {
                userName = "admin";
            } else {
                userName = (String) params.get("account");
            }

            if (params.get("bookName") != null) {
                bookName = (String) params.get("bookName");
            } else {
                int bookId = Integer.parseInt((String) params.get("bookId"));
                bookName = bookMapper.queryBookName(bookId);
            }

            Log log = new Log(0, userName, date, methodName, bookName);
            logMapper.addLog(log);

        }

        return rvt;
    }
}
