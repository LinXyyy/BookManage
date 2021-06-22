package com.xy.dao;

import com.xy.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author x1yyy
 */
public interface BookMapper {

    /**
     * 根据输入的条件对数据进行查询
     * @param type 条件
     * @return books
     */
    List<Book> queryByType(Map<String, String> type);

    /**
     * 添加数据
     * @param book 书
     * @return success
     */
    int addBook(Map<String, String> book);

    /**
     * 修改数据
     * @param book 数据
     * @return s
     */
    int updateBook(Map<String, String> book);

    /**
     * 删除
     * @param bookId 主键
     * @return x
     */
    int deleteBook(@Param("bookId") String bookId);

    /**
     * 通过书的id查找书名
     * @param bookId id
     * @return 书名
     */
    String queryBookName(@Param("bookId") int bookId);
}
