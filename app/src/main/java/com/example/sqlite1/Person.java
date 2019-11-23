package com.example.sqlite1;

public class Person {
    private int id;
    private String author;
    private String name;
    private int pages;
    private float price;

    public Person(int id,String name, String author, int pages, float price) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.price = price;
    }
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public int getpages() {
        return pages;
    }

    public void setpages(int pages) {
        this.pages = pages;
    }

    public float getprice() {
        return price;
    }

    public void setprice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  id+","+ name + "," + author + ", " + pages+ ", " + price ;
    }

}

