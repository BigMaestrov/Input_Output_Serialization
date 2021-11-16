package com.company.Halls;

import com.company.Books.IBook;
import com.company.Books.ScientificBook;
import com.company.Exceptions.BookIndexOutOfBoundsException;
import com.company.Exceptions.InvalidBookCountException;

public class ScientificLibraryHall implements IHall{
    private List scientificBooks;
    private String name;
    public List getScientificBooks() {
        return scientificBooks;
    }

    public void setScientificBooks(List scientificBooks) {
        this.scientificBooks = null;
        this.scientificBooks = new List();
        for(int i=0;i<scientificBooks.getLength();i++){
            this.scientificBooks.addToEnd(scientificBooks.getItemByID(i).getData());
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ScientificLibraryHall(String name, int numBook) throws InvalidBookCountException{
        if(numBook<0){
            throw new InvalidBookCountException();
        }
        setName(name);
        this.scientificBooks = null;
        this.scientificBooks = new List();
        for(int i=0;i<numBook;i++){
            scientificBooks.addToEnd(new ScientificBook());
        }
    }
    public ScientificLibraryHall() {
        setName("default name");
        setScientificBooks(new List());
    }
    public ScientificLibraryHall(String name, List scientificBooks) {
        setName(name);
        setScientificBooks(scientificBooks);
    }
    public void printBooks() {
        for (int i = 0; i < scientificBooks.getLength(); i++) {

            System.out.print(scientificBooks.getItemByID(i).getData().getName() + ", ");
        }
    }
    public int getCostOfAllBooks(IHall childrenLibraryHall) {
        int cost = 0;
        for (int i = 0; i < childrenLibraryHall.getScientificBooks().getLength(); i++) {
            cost += childrenLibraryHall.getScientificBooks().getItemByID(i).getData().getCost();
        }
        return cost;
    }
    public IBook getBookByID(int number) throws BookIndexOutOfBoundsException {
        if(number<0 || number > scientificBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        return this.scientificBooks.getItemByID(number).getData();
    }
    public void redactBook(IBook book, int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > scientificBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        this.scientificBooks.getItemByID(number).setData((ScientificBook) book);
    }
    public void addBook(IBook book, int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > scientificBooks.getLength()+1){
            throw new BookIndexOutOfBoundsException();
        }
        scientificBooks.addByID(number, book);
    }
    public void deleteBook(int number) throws BookIndexOutOfBoundsException{
        if(number<0 || number > scientificBooks.getLength()){
            throw new BookIndexOutOfBoundsException();
        }
        scientificBooks.removeByID(number);
    }
    public IBook getBestBook() {
        int max = 0;
        int indexMax = 0;
        for (int i = 0; i < scientificBooks.getLength(); i++) {
            if (scientificBooks.getItemByID(i).getData().getCost() >= max) {
                max = scientificBooks.getItemByID(i).getData().getCost();
                indexMax = i;
            }
        }
        return scientificBooks.getItemByID(indexMax).getData();
    }
}
