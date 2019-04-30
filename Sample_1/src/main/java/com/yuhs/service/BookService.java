package com.yuhs.service;

import com.yuhs.entity.Book;
import com.yuhs.page.entity.PageEntity;

import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface BookService {

    /**
     * 查询图书信息
     * @param pages
     * @param rows
     * @return
     */
    public PageEntity getPageList(int pages, int rows);

    /**
     * 查询所有图书
     *
     * @return
     */
    public List<Book> getList(Integer startIndex,Integer endIndex);

    /**
     * 查询一本图书
     *
     * @param bookId
     * @return
     */
    public Book getById(long bookId);

    /**
     * 图书登录
     * @param book
     */
    public void insertBook(Book book);

    /**
     * 图书更新
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 图书删除
     * @param bookId
     */
    public void deleteBook(long bookId);

}