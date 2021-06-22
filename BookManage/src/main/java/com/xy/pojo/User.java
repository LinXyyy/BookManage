package com.xy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author x1yyy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String account;
    private String password;
    private int role;
    private int bookCount;
}
