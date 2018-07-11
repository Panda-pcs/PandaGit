package com.example.pcs.fragmentcase.bean;

import com.example.pcs.fragmentcase.base.BaseModel;

import java.util.List;

/**
 * @author Starry Jerry
 * @since 2016/12/10.
 */
public class BookListBean extends BaseModel {

    private List<BookBean> books;

    private int total;

    public List<BookBean> getBooks() {
        return books;
    }

    public BookListBean setBooks(List<BookBean> books) {
        this.books = books;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
