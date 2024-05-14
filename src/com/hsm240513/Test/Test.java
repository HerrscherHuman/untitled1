package com.hsm240513.Test;
import java.util.Scanner;
import com.hsm240513.Book.BookInfo;
import com.hsm240513.Management.*;
import com.hsm240513.Until.Result;

public class Test {
    static Scanner scan=new Scanner(System.in);
    static ManagementImp manager=new ManagementImp();
    public static void main(String[] args){
        System.out.println("欢迎使用图书管理系统！");
        boolean flag=true;
        while (flag) {
            Menu();
            String msg = scan.next();
            if (msg.matches("\\d")) {
                if (returnMsg(Integer.parseInt(msg)) == 9) {
                    flag = false;
                }
            }else {
                System.out.println("输入的操作编号有误，请重新输入！");
            }
        }
    }
    static void Menu(){
            System.out.println("1.显示所有书籍");
            System.out.println("2.增加书籍");
            System.out.println("3.删除书籍");
            System.out.println("4.修改书籍");
            System.out.println("5.借出书籍");
            System.out.println("6.还回书籍");
            System.out.println("7.查看所有已借出书籍");
            System.out.println("8.查看所有未借出书籍");
            System.out.println("9.退出");
            System.out.println("请输入操作编号：");
    }
    static int returnMsg(int msg){
        switch (msg) {
            case 1:
                    manager.findAllBooks();
                    break;
            case 2:
            System.out.println("请输入书名：");
            String name = scan.next();
            result = new Result();
            result = manager.addBook(name);
            System.out.println(result.getMsg());
            break;
            case 3:
            result=new Result();
            System.out.println("请输入要删除书籍的序号：");
            String id=scan.next();
            while(!id.matches("\\d")){
                System.out.println("请输入要删除书籍的序号：");
                        id=scan.next();
                    }
                    result=manager.returnBook(Integer.parseInt(id));
                    System.out.println(result.getMsg());
                    break;
            case 4:
                result=new Result();
                System.out.println("请输入要修改书籍的序号：");
                String id1=scan.next();
                while(!id1.matches("\\d")){
                    System.out.println("请输入要修改书籍的序号：");
                    id1=scan.next();
                }
                result=manager.returnBook(Integer.parseInt(id1));
                System.out.println(result.getMsg());
                break;
            case 5:
                result=new Result();
                System.out.println("请输入要借走书籍的序号：");
                String id2=scan.next();
                while(!id2.matches("\\d")){
                    System.out.println("请输入要借走书籍的序号：");
                    id2=scan.next();
                }
                result=manager.returnBook(Integer.parseInt(id2));
                System.out.println(result.getMsg());
                break;
            case 6:
                result=new Result();
                System.out.println("请输入要还回书籍的序号：");
                String id3=scan.next();
                while(!id3.matches("\\d")){
                    System.out.println("请输入要还回书籍的序号：");
                    id3=scan.next();
                }
                result=manager.returnBook(Integer.parseInt(id3));
                System.out.println(result.getMsg());
                break;
            case 7:
                manager.findAllState1();
                break;
            case 8:
                manager.findAllState0();
                break;
            case 9:
                System.out.println("感谢使用！");
                break;
            default:
                System.out.println("输入的操作编号有误，请重新输入！");
                break;
        }
        return msg;
    }
}
