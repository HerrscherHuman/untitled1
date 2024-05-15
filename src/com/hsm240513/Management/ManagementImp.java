package com.hsm240513.Management;

import java.util.Scanner;
import com.hsm240513.Until.Result;
import com.hsm240513.Book.BookInfo;

public abstract class ManagementImp implements Management {
    BookInfo binfo = new BookInfo();
    Scanner sc = new Scanner(System.in);

    public Result addBook(String name) {
        Result result = new Result();
        try {
            int currentBookCount = binfo.bName.length - checkBook(); 
            if (isDuplicationOfName(name) == 0) {
                binfo.bName[currentBookCount] = name;
                binfo.isBorrowed[currentBookCount] = "0";
                binfo.times[currentBookCount] = 0;
                result.setState(1);
                result.setMsg("添加成功！");
            } else {
                result.setState(-1);
                result.setMsg("添加失败，书名重复！");
            }
        } catch (Exception e) {
            result.setState(-1);
            result.setMsg("添加失败，未知错误！");
        }
        return result;
    }

    public int isDuplicationOfName(String name) {
        for (int i = 0; i < binfo.bName.length; i++) {
            if (binfo.bName[i] != null && binfo.bName[i].equals(name)) {
                return i + 1;
            }
        }
        return 0;
    }

    public int checkBook() {
        int count = 0;
        for (int i = 0; i < binfo.bName.length; i++) {
            if (binfo.bName[i] == null) {
                count++;
            }
        }
        return count;
    }

    public Result removeBook(int id) {
        Result result = new Result();
        try {
            String bookName = findBookNamebyID(id);
            if (bookName != null) {
                binfo.bName[id - 1] = null;
                for (int i = id; i < binfo.bName.length; i++) {
                    binfo.bName[i - 1] = binfo.bName[i];
                    binfo.isBorrowed[i - 1] = binfo.isBorrowed[i];
                    binfo.times[i - 1] = binfo.times[i];
                }
                result.setState(2);
                result.setMsg("删除成功！");
            } else {
                result.setState(-1);
                result.setMsg("删除失败，原因为：书名不存在！");
            }
        } catch (Exception e) {
            result.setState(-1);
            result.setMsg("删除失败，原因为：" + e.getMessage());
        }
        return result;
    }

    public String findBookNamebyID(int id) {
        if (id <= binfo.bName.length) {
            return binfo.bName[id - 1];
        }
        return null;
    }

    public Result updateBooks(int id) {
        Result result = new Result();
        try {
            String getName = findBookNamebyID(id);
            if (getName != null) {
                result.setState(3);
                result.setMsg("修改成功！");
            } else {
                result.setState(-1);
                result.setMsg("修改失败，原因为：此书籍不存在！");
            }
        } catch (Exception e) {
            result.setState(-1);
            result.setMsg("修改失败，原因为：" + e.getMessage());
        }
        return result;
    }

    public void findAllBooks() {
        if (checkBook() == binfo.bName.length) {
            System.out.println("暂时没有存放书籍");
            return;
        }
        System.out.println("序号\t书名\t\t借出状态\t借出次数\t");
        for (int i = 0; i < binfo.bName.length; i++) {
            if (binfo.bName[i] != null) {
                String state = "";
                state = Integer.parseInt(binfo.isBorrowed[i]) > 0 ? "未借出" : "已借出";
                System.out.println((i + 1) + "\t" + binfo.bName[i] + "\t\t" + binfo.times[i] + "\t" + state);
            }
        }
    }

    public Result borrowBook(int id) {
        Result result = new Result();
        try {
            String bookName = findBookNamebyID(id);
            if (bookName != null) {
                if (binfo.isBorrowed[id - 1].equals("1")) {
                    result.setState(-1);
                    result.setMsg("借出失败，原因为：此书籍已借出！");
                } else {
                    binfo.isBorrowed[id - 1] = "1";
                    binfo.times[id - 1]++;
                    result.setState(5);
                    result.setMsg("借书成功");
                }
            } else {
                result.setState(-1);
                result.setMsg("借出失败，原因为：此书籍不存在！");
            }
        } catch (Exception e) {
            result.setState(-1);
            result.setMsg("借出失败，原因为：" + e.getMessage());
        }
        return result;
    }

    public Result returnbook(int id) {
        Result result = new Result();
        try {
            String bookName = findBookNamebyID(id);
            if (bookName != null) {
                if (binfo.isBorrowed[id - 1].equals("0")) {
                    result.setState(6);
                    result.setMsg("还书成功！");
                } else {
                    binfo.isBorrowed[id - 1] = "0";
                    result.setState(-1);
                    result.setMsg("还书失败，原因为：此书籍未借出！");
                }
            } else {
                result.setState(-1);
                result.setMsg("还书失败，原因为：此书籍不存在！");
            }
        } catch (Exception e) {
            result.setState(-1);
            result.setMsg("还书失败，原因为：" + e.getMessage());
        }
        return result;
    }

    public void findAllState1() {
        if (checkBook() == binfo.bName.length) {
            System.out.println("暂时没有存放书籍");
            return;
        }
        System.out.println("序号\t书名\t\t借出次数\t借出状态\t");
        for (int i = 0; i < binfo.bName.length; i++) {
            if (binfo.bName[i] != null && binfo.isBorrowed[i].equals("1")) {
                System.out.println((i + 1) + "\t" + binfo.bName[i] + "\t\t" + binfo.times[i] + "\t未借出");
            }
        }
    }

    public void findAllState0() {
        if (checkBook() == binfo.bName.length) {
            System.out.println("暂时没有存放书籍");
            return;
        }
        System.out.println("序号\t书名\t\t借出次数\t借出状态\t");
        for (int i = 0; i < binfo.bName.length; i++) {
            if (binfo.bName[i] != null && binfo.isBorrowed[i].equals("0")) {
                System.out.println((i + 1) + "\t" + binfo.bName[i] + "\t\t" + binfo.times[i] + "\t已借出");
            }
        }
    }
}
