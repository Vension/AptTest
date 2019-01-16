package com.vension.apttest.bean;


import com.kevin.vension.apt_annotation.preferences.Preferences;

/**
 * ========================================================
 * 作 者：Vension
 * 日 期：2019/1/16 12:05
 * 更 新：2019/1/16 12:05
 * 描 述：
 * ========================================================
 */

public class User {

    @Preferences
    private String name;

    @Preferences
    private int age;

    @Preferences
    private Book book;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", book=" + book +
                '}';
    }

}