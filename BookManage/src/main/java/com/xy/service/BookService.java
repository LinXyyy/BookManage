package com.xy.service;

import java.util.Map;

/**
 * @author x1yyy
 */
public interface BookService {

    /**
     * 根据输入的条件对数据进行查询
     * @param type 条件
     * @return books
     */
    Map<String, Object> queryByType(Map<String, String> type);

    /**
     * 添加数据
     * @param book 书
     * @return success
     */
    Map<String, Object> addBook(Map<String, String> book);

    /**
     * 修改数据
     * @param book 数据
     * @param bookId 要改的id
     * @return s
     */
    Map<String, Object> updateBook(Map<String, String> book, String bookId);

    /**
     * 删除
     * @param bookId 主键
     * @return x
     */
    Map<String, Object> deleteBook(String bookId);

    /**
     * 借书
     * @param bookId 借的书
     * @param account 谁借的书
     * @return y
     */
    Map<String, Object> borrowBook(String bookId, String account);

    /**
     * 还书
     * @param bookId 还的书
     * @param account 谁要还书
     * @return x
     */
    Map<String, Object> stillBook(String bookId, String account);

    Map<String, Object> queryBookOnBorrowed(String account);
}
