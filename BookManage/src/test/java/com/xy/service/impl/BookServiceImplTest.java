package com.xy.service.impl;

import com.xy.service.BookService;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class BookServiceImplTest extends TestCase {

    public void testQueryByType() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = applicationContext.getBean("bookServiceImpl", BookService.class);

        Map<String, String> map = new HashMap<>();
        System.out.println(bookService.queryByType(map));
    }

    public void testAddBook() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = applicationContext.getBean("bookServiceImpl", BookService.class);

        Map<String, String> book = new HashMap<>();
        book.put("bookName", "植物大战僵尸攻略");
        book.put("bookType", "游戏");
        book.put("publisher", "北三里出版社");
        book.put("price", "19");
        book.put("count", "80");

        bookService.addBook(book);
    }

    public void testUpdateBook() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = applicationContext.getBean("bookServiceImpl", BookService.class);

        Map<String, String> book = new HashMap<>();
        book.put("bookName", "烟与镜");
        book.put("bookType", "小说");
        bookService.updateBook(book, "5");
    }

    public void testBorrowBook() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = applicationContext.getBean("bookServiceImpl", BookService.class);

        System.out.println(bookService.borrowBook("5", "123"));
    }

    public void testStillBook() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = applicationContext.getBean("bookServiceImpl", BookService.class);

        System.out.println(bookService.stillBook("5", "123"));
    }

    public void testQueryBookOnBorrowed() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = applicationContext.getBean("bookServiceImpl", BookService.class);

        bookService.queryBookOnBorrowed("123");
    }
}