package com.yuhs.dao;

import com.yuhs.BaseTest;
import com.yuhs.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * Created by yuhaisheng on 2019/4/20.
 */
public class BookDaoTest extends BaseTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testQueryById() throws Exception {
        long bookId = 1088045;
        Book book = bookDao.queryById(bookId);
        System.out.println(book);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Book> books = bookDao.queryAll(0, 4);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testQueryPage() throws Exception {
        List<Book> books = bookDao.queryPage();
        for (Book book : books) {
            System.out.println(book);
        }
    }


}