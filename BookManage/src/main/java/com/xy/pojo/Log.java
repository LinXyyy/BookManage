package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author x1yyy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private int id;
    private String userName;
    private Date date;
    private String methodName;
    private String bookName;
}
