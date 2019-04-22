package com.yuhs.entity;

/**
 * Created by yuhaisheng on 2019/4/20.
 */
public class Book {
    private Long bookNumber;        //图书号
    private String bookName;        //书名
    private String author;          //作者
    private String publisher;       //出版社
    private String publishDate;     //出版日期
    private Long price;             //单价
    private String bookType;        //分类
    private String storeLocation;   //存放位置
    private String storeDate;       //入库日期
    private Integer borrowedNumber; //借出数量
    private Integer number;         //图书数量

    public Long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Long bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
    }

    public Integer getBorrowedNumber() {
        return borrowedNumber;
    }

    public void setBorrowedNumber(Integer borrowedNumber) {
        this.borrowedNumber = borrowedNumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookNumber=" + bookNumber +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", price=" + price +
                ", bookType='" + bookType + '\'' +
                ", storeLocation='" + storeLocation + '\'' +
                ", storeDate='" + storeDate + '\'' +
                ", borrowedNumber=" + borrowedNumber +
                ", number=" + number +
                '}';
    }
}
