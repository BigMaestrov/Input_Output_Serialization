package com.company.Librarys;

import com.company.Books.IBook;
import com.company.Books.ChildrenBook;
import com.company.Exceptions.BookIndexOutOfBoundsException;
import com.company.Exceptions.HallIndexOutOfBoundsException;
import com.company.Halls.IHall;

import java.io.IOException;
import java.io.Writer;

public class ChildrenLibrary implements ILibrary{
    int numHalls;
    BidirectionalList childrenBooks;

    public String getType(){
        return "children";
    }

    public int getNumHalls() {
        return numHalls;
    }

    public void setNumHalls(int numHalls) {
        this.numHalls = numHalls;
    }

    public BidirectionalList getLibraryHalls() {
        return childrenBooks;
    }

    public IHall getLibraryHallsByID(int id) {
        return childrenBooks.getItemByID(id).getData();
    }

    public IBook getBookByID(int id)throws BookIndexOutOfBoundsException {
        if(id<0 || id>sumOfAllBooks()){
            throw new BookIndexOutOfBoundsException();
        }
        IBook[] booksInLibrary = new IBook[sumOfAllBooks()];
        int numBookInLibrary = 0;
        //Запись в новый массив
        for (int i = 0; i < childrenBooks.getLength(); i++) {
            for (int j = 0; j < childrenBooks.getItemByID(i).getData().getBooks().getLength(); j++) {
                booksInLibrary[numBookInLibrary] = childrenBooks.getItemByID(i).getData().getBookByID(j);
                numBookInLibrary++;
            }
        }
        return booksInLibrary[id];
    }

    public void setLibraryHalls(BidirectionalList scientificLibraryHalls) {
        this.childrenBooks = null;
        this.childrenBooks = new BidirectionalList();
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {

            this.childrenBooks.addToEnd(scientificLibraryHalls.getItemByID(i).getData());
        }
    }

    public ChildrenLibrary(BidirectionalList scientificLibraryHalls) {
        setNumHalls(scientificLibraryHalls.getLength());
        setLibraryHalls(scientificLibraryHalls);
    }

    public ChildrenLibrary() {
        setNumHalls(0);
    }

    public int sumOfAllBooks() {
        int numBook = 0;
        for (int i = 0; i < getNumHalls(); i++) {
            numBook +=
                    childrenBooks.getItemByID(i).getData().getBooks().getLength();
        }
        return numBook;
    }

    public IBook[] selectionSortBookInHallByCost() {
        IBook[] booksInLibrary = new IBook[sumOfAllBooks()];
        int numBookInLibrary = 0;
        //Запись в новый массив
        for (int i = 0; i < childrenBooks.getLength(); i++) {
            for (int j = 0; j <
                    childrenBooks.getItemByID(i).getData().getBooks().getLength(); j++) {
                booksInLibrary[numBookInLibrary] = childrenBooks.getItemByID(i).getData().getBookByID(j);
                numBookInLibrary++;
            }
        }
        //Сортировка
        for (int left = 0; left < booksInLibrary.length; left++) {
            int maxInd = left;
            for (int i = left; i < booksInLibrary.length; i++) {
                if (booksInLibrary[i].getCost() >
                        booksInLibrary[maxInd].getCost()) {
                    maxInd = i;
                }
            }
            swap(booksInLibrary, left, maxInd);
        }
        return booksInLibrary;
    }

    public void swap(IBook[] books, int left, int minId) {
        IBook book = books[left];
        books[left] = books[minId];
        books[minId] = book;
    }

    public void printNamesAndNumBooksOfHalls() {
        for (int i = 0; i < getNumHalls(); i++) {
            System.out.println("Name:" +
                    childrenBooks.getItemByID(i).getData().getName() + ", NumOfBook:" +
                    childrenBooks.getItemByID(i).getData().getBooks().getLength());
        }
    }

    public void changeHallByID(int numHall, IHall newHall) throws HallIndexOutOfBoundsException {
        if(numHall<0 || numHall>getNumHalls()){
            throw new HallIndexOutOfBoundsException();
        }
        childrenBooks.removeByID(numHall);
        childrenBooks.addByID(numHall, newHall);
    }

    public void changeBookByID(int num, IBook book) {
        int IDofBook = 0;
        //Запись в новый массив
        for (int i = 0; i < childrenBooks.getLength(); i++) {
            for (int j = 0; j <

                    childrenBooks.getItemByID(i).getData().getBooks().getLength(); j++){
                if (num == IDofBook) {

                    childrenBooks.getItemByID(i).getData().redactBook(book, j);
                }
                IDofBook++;
            }
        }
    }

    public void addBookByID(int number, IBook book) {
        if (number < 0) {
            return;
        }
        if (number > sumOfAllBooks()) {
            return;
        }
        int numBookInLibrary = 0;
        for (int i = 0; i < childrenBooks.getLength(); i++) {
            for (int j = 0; j <
                    childrenBooks.getItemByID(i).getData().getBooks().getLength(); j++, numBookInLibrary++) {
                if (numBookInLibrary == number) {

                    childrenBooks.getItemByID(i).getData().getBooks().addByID(number, book);
                }
            }
        }
    }

    public void deleteBookFromLibrary(int number) {
        if (number < 0) {
            return;
        }
        if (number > sumOfAllBooks()) {
            return;
        }
        int numBookInLibrary = 0;
        for (int i = 0; i < childrenBooks.getLength(); i++) {
            for (int j = 0; j <
                    childrenBooks.getItemByID(i).getData().getBooks().getLength(); j++, numBookInLibrary++) {
                if (numBookInLibrary == number) {

                    childrenBooks.getItemByID(i).getData().getBooks().removeByID(number);
                }
            }
        }
    }

    public IBook getBestBook() {
        IBook bestBook = new ChildrenBook();
        for (int i = 0; i < childrenBooks.getLength(); i++) {
            if (bestBook.getCost() <
                    childrenBooks.getItemByID(i).getData().getBestBook().getCost())
                bestBook = childrenBooks.getItemByID(i).getData().getBestBook();
        }
        return bestBook;
    }

    public void printBooks() {
        for (int i = 0; i < childrenBooks.getLength(); i++) {

            childrenBooks.getItemByID(i).getData().printBooks();
        }
    }

    public void printBooks(IBook[] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].toString());
        }
    }

    public String toString(){
        String buffer = "";
        buffer+=getLibraryHalls().getLength()+"\n";
        for(int i=0;i<getLibraryHalls().getLength();i++){
            buffer+=getBookByID(i).toString();
        }
        return buffer;
    }

    public void writeInFile(Writer out) {
        try {
            out.write(getType()+"\n");
            out.write(this.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
