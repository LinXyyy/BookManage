package com.xy.dao;

import com.xy.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author x1yyy
 */
public interface BookAndUserMapper {

    /**
     * 借书时在关联表中插入记录
     * @param account 借书人
     * @param bookId 书
     * @param date 借书日期
     * @return success : failed
     */
    int addRecord(@Param("account") String account, @Param("bookId") String bookId, @Param("date") Date date);

    /**
     * 还书时删除记录
     * @param bookId 书
     * @param account 还书人
     * @return success : failed
     */
    int deleteRecord(@Param("bookId") String bookId, @Param("account") String account);

    /**
     * 查询是否已拥有此书
     * @param bookId 书
     * @param account 人
     * @return 是否拥有
     */
    Object queryRecord(@Param("bookId") String bookId, @Param("account") String account);

    /**
     * 查询正在借的书
     * @param account 人
     * @return 是
     */
    List<Book> queryBookOnBorrowed(@Param("account") String account);

    /**
     * 查询某书是否被借
     * @param bookId 书
     * @return s
     */
    Object queryBookIsBorrowed(@Param("bookId") String bookId);
}
