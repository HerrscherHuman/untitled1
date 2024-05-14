package com.hsm240513.Management;
import com.hsm240513.Until.Result;
public interface Management {
    Result addBook(String name);
    Result removeBook(int id);
    Result updateBooks(int id);
    void findAllBooks();
    Result borrowBook(int id);
    Result returnBook(int id);
    void findAllState1();
    void findAllState0();
}