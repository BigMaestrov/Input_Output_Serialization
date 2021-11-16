package com.company.Books;

public class ScientificBook extends Book {
    private double citationIndex;
    public double getCitationIndex() {
        return citationIndex;
    }
    public void setCitationIndex(double citationIndex) {
        this.citationIndex = citationIndex;
    }
    public ScientificBook() {
        super();
        setCitationIndex(0.0);
    }
    public ScientificBook(String author, String name, int cost, int year, int citationIndex) {
        super(author, name, cost, year);
        setCitationIndex(citationIndex);
    }
    public ScientificBook(String author, int year) {
        this();
        setAuthor(author);
        setYear(year);
    }
    public static String toString(ScientificBook book){
        return book.getAuthor() + " " + book.getName() + " " +
                book.getCost() + " " + book.getYear() + " "+ book.getCitationIndex();
    }
    public void printBook(){
        System.out.println(getAuthor() + " " + getName() + " " + getCost()
                + " " + getYear() + " "+ getCitationIndex());
    }
}
