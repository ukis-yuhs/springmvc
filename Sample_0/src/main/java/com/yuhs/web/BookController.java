package com.yuhs.web;

import com.yuhs.dto.ResponseJsonResult;
import com.yuhs.entity.Book;
import com.yuhs.page.entity.PageEntity;
import com.yuhs.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book") // url:/模块/资源/细分/{id}
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;

    /**
     * 取得指定页图书内容
     * @param page 开始行
     * @param rows 结束行
     * @return
     */
    @RequestMapping(value = "/page/{page}/{rows}", method = RequestMethod.GET)
    @ResponseBody
    private PageEntity page(@PathVariable(value = "page")int page, @PathVariable(value = "rows")int rows) {
        PageEntity pageEntity = bookService.getPageList(page,rows);
        return pageEntity;
    }

    /**
     * 取得图书一览内容
     * @param startIndex 开始行
     * @param endIndex 结束行
     * @return
     */
    @RequestMapping(value = "/list/{startIndex}/{endIndex}", method = RequestMethod.GET)
    @ResponseBody
    private ResponseJsonResult list(@PathVariable(value = "startIndex") Integer startIndex,
                                    @PathVariable(value = "endIndex") Integer endIndex) {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        List<Book> list = bookService.getList(startIndex,endIndex);
        responseJsonResult.setList(list);
        return responseJsonResult;
    }

    /**
     * 取得指定图书
     * @param bookId 图书号
     * @return
     */
    @RequestMapping(value = "/detail/{bookId}", method = RequestMethod.GET)
    @ResponseBody
    private ResponseJsonResult detail(@PathVariable("bookId") Long bookId) {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        Book book = bookService.getById(bookId);
        if (book == null) {
            responseJsonResult.setStatus(404);
            responseJsonResult.setMsg("输入图书号错误");
        }
        responseJsonResult.setObj(book);
        return responseJsonResult;
    }

    /**
     * 新图书登录
     * @param book
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    private ResponseJsonResult insertBook(@RequestBody Book book) {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        bookService.insertBook(book);
        return responseJsonResult;
    }

    /**
     * 图书信息更新
     * @param bookId
     * @param book
     * @return
     */
    @RequestMapping(value = "/update/{bookId}", method = RequestMethod.POST)
    @ResponseBody
    private ResponseJsonResult updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        book.setBookNumber(bookId);
        bookService.updateBook(book);
        return responseJsonResult;
    }

    /**
     * 图书信息更新
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.POST)
    @ResponseBody
    private ResponseJsonResult deleteBook(@PathVariable("bookId") Long bookId) {
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        bookService.deleteBook(bookId);
        return responseJsonResult;
    }

}
