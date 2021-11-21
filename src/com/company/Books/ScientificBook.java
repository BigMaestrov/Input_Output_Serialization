package com.company.Books;

import java.io.IOException;
import java.io.Writer;

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
    public  String toString(){
        return this.getAuthor() + "\n" + this.getName() + "\n" +
                this.getCost() + "\n" + this.getYear() + "\n"+ this.getCitationIndex();
    }
    public void printBook(){
        System.out.println(getAuthor() + " " + getName() + " " + getCost()
                + " " + getYear() + " "+ getCitationIndex());
    }

    public void writeInFile(Writer out) {
        try {
            out.write(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
