package com.company;

import com.company.Books.ScientificBook;
import com.company.Halls.List;
import com.company.Halls.ScientificLibraryHall;
import com.company.Librarys.BidirectionalList;
import com.company.Librarys.ChildrenLibrary;
import com.company.Librarys.ILibrary;
import com.company.Librarys.ScientificLibrary;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Libraries {

    //Запись данных о библиотеке в байтовый поток
    public static void outputLibrary(ILibrary lib, OutputStream out) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(lib);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Чтение данных о библиотеке из байтового потока
    public static ILibrary inputLibrary(InputStream in) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            return (ILibrary) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //записи библиотеки в символьный поток
    public static void writeLibrary(ILibrary lib, Writer out) {
        lib.writeInFile(out);
    }

    //чтения библиотеки из символьного потока
    public static ILibrary readLibrary(Reader in) {
        try (Scanner scanner = new Scanner(in)) {
            String buffer = scanner.nextLine();
            ILibrary library;
            if (buffer.equals("science")) {
                //Запись библиотеки
                library = new ScientificLibrary();
                library.setNumHalls(scanner.nextInt());
                scanner.nextLine();
                int intBuffer = 0;
                //Запись Зала
                for (int i = 0; i < library.getNumHalls(); i++) {
                    library.getLibraryHalls().addToEnd(new ScientificLibraryHall());
                    library.getLibraryHallsByID(i).setName(scanner.nextLine());
                    intBuffer = scanner.nextInt();
                    scanner.nextLine();
                    //Запись Книги
                    for (int j = 0; j < intBuffer; j++) {
                        ScientificBook book = new ScientificBook();

                        book.setAuthor(scanner.nextLine());

                        book.setName(scanner.nextLine());

                        book.setCost(scanner.nextInt());
                        scanner.nextLine();

                        book.setYear(scanner.nextInt());
                        scanner.nextLine();

                        book.setCitationIndex(12.0);
                        scanner.nextLine();

                        library.getLibraryHallsByID(i).addBook(book, j);
                    }
                }
                return library;
            } else if (buffer.equals("children")) {
                library = new ChildrenLibrary();
                return library;
            } else {
                System.err.println("INCORRECT TYPE OF LIBRARY");
                return null;
            }
        }
    }
}
