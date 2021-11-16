package com.company.Librarys;

import com.company.Books.IBook;
import com.company.Halls.IHall;

public interface ILibrary {

    public int getNumHalls();

    public void setNumHalls(int numHalls);

    public BidirectionalList getScientificLibraryHalls();

    public IHall getChildrenLibraryHallsByID(int id);

    public IBook getBookByID(int id);

    public void setScientificLibraryHalls(BidirectionalList scientificLibraryHalls) ;

    public int sumOfAllBooks();

    public IBook[] selectionSortBookInHallByCost() ;

    void swap(IBook[] books, int left, int minId);

    public void printNamesAndNumBooksOfHalls();

    public void changeHallByID(int numHall, IHall newHall);

    public void changeBookByID(int num, IBook book);

    public void addBookByID(int number, IBook book);

    public void deleteBookFromLibrary(int number);

    public IBook getBestBook();

    public void printBooks();

    public void printBooks(IBook[] books);
}
