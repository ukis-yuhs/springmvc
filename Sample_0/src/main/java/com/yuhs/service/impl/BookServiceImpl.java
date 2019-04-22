package com.yuhs.service.impl;

import com.yuhs.dao.BookDao;
import com.yuhs.entity.Book;
import com.yuhs.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 注入Service依赖
    @Autowired
    private BookDao bookDao;

    /**
     * 取得图书一览内容
     * @param startIndex
     * @param endIndex
     * @return
     */
    public List<Book> getList(Integer startIndex,Integer endIndex) {
        return bookDao.queryAll(startIndex, endIndex);
    }


    /**
     * 取得指定图书
     * @param bookId
     * @return
     */
    public Book getById(long bookId) {
        return bookDao.queryById(bookId);
    }

    /**
     * 图书登录
     * @param book
     */
    public void insertBook(Book book) {
        bookDao.insertBookInfo(book);

    }

    /**
     * 图书更新
     * @param book
     */
    public void updateBook(Book book) {
        bookDao.updateByPrimaryKeySelective(book);
    }

    /**
     * 图书删除
     * @param bookId
     */
    public void deleteBook(long bookId) {
        bookDao.deleteByPrimaryKey(bookId);
    }
}