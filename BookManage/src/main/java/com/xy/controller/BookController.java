package com.xy.controller;

import com.xy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author x1yyy
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @ResponseBody
    @RequestMapping("/queryBook")
    public Map<String, Object> queryByType(String bookName, String bookType, String publisher) {
        Map<String, String> type = new HashMap<>();
        type.put("bookName", bookName);
        type.put("bookType", bookType);
        type.put("publisher", publisher);
        return bookService.queryByType(type);
    }

    @ResponseBody
    @RequestMapping("/addBook")
    public Map<String, Object> addBook(String bookName, String bookType, String publisher, String price, String count) {
        Map<String, String> map = stringArrayToMap(bookName, bookType, publisher, price, count);
        return bookService.addBook(map);
    }

    @ResponseBody
    @RequestMapping("/updateBook")
    public Map<String, Object> updateBook(String bookId,String bookName, String bookType, String publisher, String price, String count) {
        Map<String, String> book = stringArrayToMap(bookName, bookType, publisher, price, count);
        return bookService.updateBook(book, bookId);
    }

    @ResponseBody
    @RequestMapping("/deleteBook")
    public Map<String, Object> deleteBook(String bookId) {
        return bookService.deleteBook(bookId);
    }

    @ResponseBody
    @RequestMapping("/borrowBook")
    public Map<String, Object> borrowBook(String bookId, @RequestHeader String account) {
        return bookService.borrowBook(bookId, account);
    }

    @ResponseBody
    @RequestMapping("/stillBook")
    public Map<String, Object> stillBook(String bookId, @RequestHeader String account) {
        return bookService.stillBook(bookId, account);
    }

    @ResponseBody
    @RequestMapping("/queryBookOnBorrowed")
    public Map<String, Object> queryBookOnBorrowed(@RequestHeader String account) {
        return bookService.queryBookOnBorrowed(account);
    }

    private Map<String, String> stringArrayToMap(String bookName, String bookType, String publisher, String price, String count) {
        Map<String, String> book = new HashMap<>();
        book.put("bookName", bookName);
        book.put("bookType", bookType);
        book.put("publisher", publisher);
        book.put("price", price);
        book.put("count", count);
        return book;
    }
}
