package com.yuhs.dao;

import com.yuhs.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yuhaisheng on 2019/4/20.
 */
public interface BookDao {

    /**
     * 指定页图书
     * @return
     */
    List<Book> queryPage();

    /**
     * 查询指定区间图书
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return
     */
    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过ID查询单本图书
     *
     * @param id
     * @return
     */
    Book queryById(long id);

    /**
     * 图书登录
     * @param book
     */
    void insertBookInfo(Book book);

    /**
     * 图书更新
     * @param book
     */
    void updateByPrimaryKeySelective(Book book);

    /**
     * 图书删除
     * @param bookId
     */
    void deleteByPrimaryKey(long bookId);
}
