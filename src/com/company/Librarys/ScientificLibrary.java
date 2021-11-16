package com.company.Librarys;

import com.company.Books.IBook;
import com.company.Books.ScientificBook;
import com.company.Exceptions.BookIndexOutOfBoundsException;
import com.company.Exceptions.HallIndexOutOfBoundsException;
import com.company.Halls.IHall;

public class ScientificLibrary implements ILibrary{
    int numHalls;
    BidirectionalList scientificLibraryHalls;

    public int getNumHalls() {
        return numHalls;
    }

    public void setNumHalls(int numHalls) {
        this.numHalls = numHalls;
    }

    public BidirectionalList getScientificLibraryHalls() {
        return scientificLibraryHalls;
    }

    public IHall getChildrenLibraryHallsByID(int id) {
        return scientificLibraryHalls.getItemByID(id).getData();
    }

    public IBook getBookByID(int id)throws BookIndexOutOfBoundsException {
        if(id<0 || id>sumOfAllBooks()){
            throw new BookIndexOutOfBoundsException();
        }
        IBook[] booksInLibrary = new IBook[sumOfAllBooks()];
        int numBookInLibrary = 0;
        //Запись в новый массив
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {
            for (int j = 0; j < scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().getLength(); j++) {
                booksInLibrary[numBookInLibrary] = scientificLibraryHalls.getItemByID(i).getData().getBookByID(j);
                numBookInLibrary++;
            }
        }
        return booksInLibrary[id];
    }

    public void setScientificLibraryHalls(BidirectionalList scientificLibraryHalls) {
        this.scientificLibraryHalls = null;
        this.scientificLibraryHalls = new BidirectionalList();
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {

            this.scientificLibraryHalls.addToEnd(scientificLibraryHalls.getItemByID(i).getData());
        }
    }

    public ScientificLibrary(BidirectionalList scientificLibraryHalls) {
        setNumHalls(scientificLibraryHalls.getLength());
        setScientificLibraryHalls(scientificLibraryHalls);
    }

    public int sumOfAllBooks() {
        int numBook = 0;
        for (int i = 0; i < getNumHalls(); i++) {
            numBook +=
                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().getLength();
        }
        return numBook;
    }

    public IBook[] selectionSortBookInHallByCost() {
        IBook[] booksInLibrary = new IBook[sumOfAllBooks()];
        int numBookInLibrary = 0;
        //Запись в новый массив
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {
            for (int j = 0; j <
                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().getLength(); j++) {
                booksInLibrary[numBookInLibrary] = scientificLibraryHalls.getItemByID(i).getData().getBookByID(j);
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
                    scientificLibraryHalls.getItemByID(i).getData().getName() + ", NumOfBook:" +
                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().getLength());
        }
    }

    public void changeHallByID(int numHall, IHall newHall) throws HallIndexOutOfBoundsException {
        if(numHall<0 || numHall>getNumHalls()){
            throw new HallIndexOutOfBoundsException();
        }
        scientificLibraryHalls.removeByID(numHall);
        scientificLibraryHalls.addByID(numHall, newHall);
    }

    public void changeBookByID(int num, IBook book) {
        int IDofBook = 0;
        //Запись в новый массив
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {
            for (int j = 0; j <

                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().getLength(); j++){
                if (num == IDofBook) {

                    scientificLibraryHalls.getItemByID(i).getData().redactBook(book, j);
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
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {
            for (int j = 0; j <
                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().getLength(); j++, numBookInLibrary++) {
                if (numBookInLibrary == number) {

                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().addByID(number, book);
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
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {
            for (int j = 0; j <
                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().getLength(); j++, numBookInLibrary++) {
                if (numBookInLibrary == number) {

                    scientificLibraryHalls.getItemByID(i).getData().getScientificBooks().removeByID(number);
                }
            }
        }
    }

    public IBook getBestBook() {
        IBook bestBook = new ScientificBook();
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {
            if (bestBook.getCost() <
                    scientificLibraryHalls.getItemByID(i).getData().getBestBook().getCost())
                bestBook = scientificLibraryHalls.getItemByID(i).getData().getBestBook();
        }
        return bestBook;
    }

    public void printBooks() {
        for (int i = 0; i < scientificLibraryHalls.getLength(); i++) {

            scientificLibraryHalls.getItemByID(i).getData().printBooks();
        }
    }

    public void printBooks(IBook[] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].toString());
        }
    }
}
