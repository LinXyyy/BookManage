package com.xy.service.impl;

import com.xy.dao.BookAndUserMapper;
import com.xy.dao.BookMapper;
import com.xy.dao.UserMapper;
import com.xy.pojo.Book;
import com.xy.pojo.User;
import com.xy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author x1yyy
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookAndUserMapper bookAndUserMapper;

    @Override
    public Map<String, Object> queryByType(Map<String, String> type) {
        Map<String, Object> results = new HashMap<>();

        if (type.get("bookName") != null) {
            type.put("bookName", "%" + type.get("bookName") + "%");
        }

        List<Book> books = bookMapper.queryByType(type);

        if (books.size() == 0) {
            results.put("resultCode", 1);
            results.put("resultMessage", "books is null");
            results.put("data", null);
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("books", books);
            results.put("resultCode", 0);
            results.put("resultMessage", "success");
            results.put("data", data);
        }

        return results;
    }

    @Override
    public Map<String, Object> addBook(Map<String, String> book) {
        Map<String, Object> results = new HashMap<>();

        List<Book> books = bookMapper.queryByType(book);
        if (books.size() > 0) {
            results.put("resultCode", 2);
            results.put("resultMessage", "have");
            results.put("data", null);

            return results;
        }

        if (bookMapper.addBook(book) != 1) {
            results.put("resultCode", 1);
            results.put("resultMessage", "failed");
            results.put("data", null);
        } else {
            results.put("resultCode", 0);
            results.put("resultMessage", "success");
            results.put("data", null);
        }
        return results;
    }

    @Override
    public Map<String, Object> updateBook(Map<String, String> book, String bookId) {
        Map<String, Object> results = new HashMap<>();

        book.put("bookId", bookId);
        if (bookMapper.updateBook(book) != 1) {
            results.put("resultCode", 1);
            results.put("resultMessage", "failed");
            results.put("data", null);
        } else {
            results.put("resultCode", 0);
            results.put("resultMessage", "success");
            results.put("data", null);
        }

        return results;
    }

    @Override
    public Map<String, Object> deleteBook(String bookId) {
        Map<String, Object> results = new HashMap<>();

        Object o = bookAndUserMapper.queryBookIsBorrowed(bookId);
        if (o != null) {
            results.put("resultCode", 2);
            results.put("resultMessage", "the book is on borrowed");
            results.put("data", null);
            return results;
        }

        if (bookMapper.deleteBook(bookId) != 1) {
            results.put("resultCode", 1);
            results.put("resultMessage", "failed");
            results.put("data", null);
        } else {
            results.put("resultCode", 0);
            results.put("resultMessage", "success");
            results.put("data", null);
        }

        return results;
    }

    @Override
    public Map<String, Object> borrowBook(String bookId, String account) {
        Map<String, Object> results = new HashMap<>();

        Map<String, String> queryBook = new HashMap<>();
        queryBook.put("bookId", bookId);

        List<Book> getBook = bookMapper.queryByType(queryBook);

        Book book = getBook.get(0);
        User user = userMapper.queryUserByAccount(account);

        int userCount = user.getBookCount();

        Object object = bookAndUserMapper.queryRecord(bookId, account);

        if ( object != null ) {
            results.put("resultCode", 3);
            results.put("resultMessage", "you have the book");
            results.put("data", null);
            return results;
        }
        if ( userCount >= 3 ) {
            results.put("resultCode", 1);
            results.put("resultMessage", "user limit");
            results.put("data", null);
            return results;
        }
        if ( book.getCount() <= 0) {
            results.put("resultCode", 2);
            results.put("resultMessage", "book is null");
            results.put("data", null);
            return results;
        }

        int nowCount =  getBook.get(0).getCount() - 1;
        int userUpdate = userMapper.updateUser(userCount + 1, account);
        queryBook.put("count", nowCount + "");
        int bookUpdate = bookMapper.updateBook(queryBook);

        Date date = new Date();
        int updateOk = bookAndUserMapper.addRecord(account, bookId, date);

        results.put("resultCode", 0);
        results.put("resultMessage", "success");
        results.put("data", null);
        return results;
    }

    @Override
    public Map<String, Object> stillBook(String bookId, String account) {
        Map<String, Object> results = new HashMap<>();

        User user = userMapper.queryUserByAccount(account);
        int nowCount = user.getBookCount() - 1;
        int userUpdate = userMapper.updateUser(nowCount, account);

        Map<String, String> updateBook = new HashMap<>();
        updateBook.put("bookId", bookId);
        List<Book> getBook = bookMapper.queryByType(updateBook);
        Book book = getBook.get(0);
        updateBook.put("count", book.getCount() + 1 + "");
        int bookUpdate = bookMapper.updateBook(updateBook);

        int deleteOk = bookAndUserMapper.deleteRecord(bookId, account);

        results.put("resultCode", 0);
        results.put("resultMessage", "success");
        results.put("data", null);
        return results;
    }

    @Override
    public Map<String, Object> queryBookOnBorrowed(String account) {
        Map<String, Object> results = new HashMap<>();
        List<Book> books = bookAndUserMapper.queryBookOnBorrowed(account);
        results.put("resultCode", 0);
        results.put("resultMessage", "success");
        results.put("data", books);
        return results;
    }
}
