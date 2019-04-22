package com.yuhs.service.impl;

import com.yuhs.BaseTest;
import com.yuhs.entity.Book;
import com.yuhs.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yuhaisheng on 2019/4/20.
 */
public class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testGetList() throws Exception {
        List<Book> bookInfo = bookService.getList(0,3);
        System.out.println(bookInfo);
    }

}