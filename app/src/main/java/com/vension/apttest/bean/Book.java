package com.vension.apttest.bean;

/**
 * ========================================================
 * 作 者：Vension
 * 日 期：2019/1/16 12:05
 * 更 新：2019/1/16 12:05
 * 描 述：
 * ========================================================
 */

public class Book {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
