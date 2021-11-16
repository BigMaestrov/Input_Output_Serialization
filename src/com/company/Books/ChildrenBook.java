package com.company.Books;

public class ChildrenBook extends Book {
    private int minimalAge;
    public int getMinimalAge() {
        return minimalAge;
    }
    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }
    public ChildrenBook(int minimalAge) {
        this.minimalAge = minimalAge;
    }
    public ChildrenBook() {
        super();
        setMinimalAge(0);
    }
    public ChildrenBook(String author, String name, int cost, int year, int minimalAge) {
        super(author, name, cost, year);
        setMinimalAge(minimalAge);
    }
    public ChildrenBook(String author, int year) {
        this();
        setAuthor(author);
        setYear(year);
    }
    public String toString(){
        return getAuthor() + " " + getName() + " " + getCost() +
                " " + getYear() + " "+ getMinimalAge();
    }
}
