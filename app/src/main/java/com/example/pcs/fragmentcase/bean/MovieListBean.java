package com.example.pcs.fragmentcase.bean;

import com.example.pcs.fragmentcase.base.BaseModel;

import java.util.List;

/**
 * @author pcs
 * @since 2018-07-07.
 */
public class MovieListBean extends BaseModel {
    private int count;
    private int start;
    private int total;
    private List<MovieBean> subjects;
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MovieBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<MovieBean> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
