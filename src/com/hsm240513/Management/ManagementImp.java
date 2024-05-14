package com.hsm240513.Management;
import java.util.Scanner;
import com.hsm240513.Until.Result;
import com.hsm240513.Book.BookInfo;

public class ManagementImp implements Management {
    BookInfo binfo = new BookInfo();
    static Scanner sc = new Scanner(System.in);

    public Result addBook(String name) {
        Result result = new Result();
        try {
            int startLen = checkBook();
            if (isDuplicationOfName(name) == 0) {
                binfo.bName[startLen] = name;
                binfo.isBorrowed[startLen] = "0";
                binfo.times[startLen] = 0;
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
        int DumplicationOfName = 0;
        for (int i = 0; i < binfo.bName.length; i++) {
            if (binfo.bName[i] != null) {
                if (binfo.bName[i].equals(name)) {
                    DumplicationOfName = i + 1;
                    break;
                }
            }
        }
        return DumplicationOfName;
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
            int state = isDuplicationOfName(findBookNamebyID(id));
            if (state > 0) {
                binfo.bName[id - 1] = null;
                result.setState(2);
                result.setMsg("删除成功！");
                for (int i = id; i < binfo.bName.length; i++) {
                    binfo.bName[i - 1] = binfo.bName[i];
                    binfo.bName[i] = null;
                    binfo.isBorrowed[i - 1] = binfo.isBorrowed[i];
                    binfo.times[i - 1] = binfo.times[i];
                }
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
        String name = null;
        int saveCount = checkBook();
        if (id <= saveCount) {
            name = binfo.bName[id - 1];
        }
        return name;
    }

    public Result updateBooks(int id) {
        Result result = new Result();
        try {
            String getName = findBookNamebyID(id);
            int state = isDuplicationOfName(getName);
            if (state > 0) {
                System.out.println("是否要修改书籍名称：y/n");
                String name = scan.next();
                while (!name.equals("y") && !name.equals("n")) {
                    System.out.print("是否要修改书籍名称：y/n");
                    name = scan.next();
                }
                if (name.equals("y")) {
                    System.out.println("请输入新的书籍名称：");
                    String bname = scan.next();
                    while (isDuplicationOfName(bname) > 0) {
                        System.out.println("书籍名称重复，请重新输入：");
                        System.out.println("请输入新的书籍名称：");
                        bname = scan.next();
                    }
                    binfo.bName[id - 1] = bname;
                }
                System.out.print("是否要修改书籍借出次数：y/n");
                String times = scan.next();
                while (!times.equals("y") && !times.equals("n")) {
                    System.out.print("是否要修改书籍借出次数：y/n");
                    times = scan.next();
                }
                if (times.equals("y")) {
                    System.out.println("请输入数字：");
                    String time = scan.next();
                    while (!time.matches("\\d")) {
                        System.out.println("请输入数字：");
                        time = scan.next();
                    }
                    binfo.times[id - 1] = Integer.parseInt(time);
                }
                System.out.println("是否要修改书籍借出状态：y/n");
                String isBorrow = scan.next();
                while (!isBorrow.equals("y") && !isBorrow.equals("n")) {
                    System.out.print("是否要修改书籍借出状态：y/n");
                    isBorrow = scan.next();
                }
                if (isBorrow.equals("y")) {
                    System.out.println("1为已借出，0为未借出");
                    String borrow = scan.next();
                    while (!borrow.matches("\\d")) {
                        System.out.println("1为已借出，0为未借出");
                        borrow = scan.next();
                    }
                    if (Integer.parseInt(borrow) < 0) {
                        System.out.println("系统更正为：0（未借出）");
                        binfo.isBorrowed[id - 1] = 0;
                    } else if (Integer.parseInt(borrow) > 1) {
                        System.out.println("系统更正为：1（已借出）");
                        binfo.isBorrowed[id - 1] = 1;
                    } else {
                        binfo.isBorrowed[id - 1] = Integer.parseInt(borrow);
                    }
                }
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
    public void findAllBooks(){
        int savecount=checkBook();
        if(savecount>0){
            System.out.println();
            System.out.println("暂时没有存放书籍");
            System.out.println();
            return;
        }
        System.out.println("序号\t书名\t\t借出状态\t借出次数\t");
        for(int i=0;i<binfo.bName.length;i++){
            if(binfo.bName[i]!=null){
                String state="";
                System.out.println((i+1)+"\t"+binfo.bName[i]+"\t\t"+binfo.times[i]+"\t"+(state=binfo.isBorrowed[i]>0?"未借出":"已借出"));
            }
        }
    }
    public Result borrowBook(int id){
        Result result=new Result();
        try{
            int isExist=isDuplicationOfName(findBookNamebyID(id));
            if(isExist>0){
                if(binfo.isBorrowed[id-1]==1){
                    result.setState(-1);
                    result.setMsg("借出失败，原因为：此书籍已借出！");
                }else{
                    binfo.isBorrowed[id-1]=1;
                    binfo.times[id-1]=binfo.times[id-1]+1;
                    result.setState(5);
                    result.setMsg("借书成功");
                }
            }else{
                result.setState(-1);
                result.setMsg("借出失败，原因为：此书籍不存在！");
            }
        }catch(Exception e){
            result.setState(-1);
            result.setMsg("借出失败，原因为："+e.getMessage());
        }
        return result;
    }
    public Result returnbook(int id){
        Result result=new Result();
        try{
            int isExist=isDuplicationOfName(findBookNamebyID(id));
            if(isExist>0){
                if(binfo.isBorrowed[id-1]==0){
                    result.setState(6);
                    result.setMsg("还书成功！");
                }else{
                    binfo.isBorrowed[id-1]=0;

                    result.setState(-1);
                    result.setMsg("还书失败，原因为：此书籍未借出！");
                }
            }else{
                result.setState(-1);
                result.setMsg("还书失败，原因为：此书籍不存在！");
            }
        }catch(Exception e){
            result.setState(-1);
            result.setMsg("还书失败，原因为："+e.getMessage());
        }
        return result;
    }
    public void findAllState1(){
        int savecount=checkBook();
        int notBorrowCount=0;
        int borrowCount=0;
        if(savecount>0){
            System.out.println("暂时没有存放书籍");
            return;
        }
        System.out.println("序号\t书名\t\t借出次数\t借出状态\t");
        for(int i=0;i<binfo.bName.length;i++){
            if(binfo.bName[i]!=null){
                if(binfo.isBorrowed[i]==1){
                    System.out.println((i+1)+"\t"+binfo.bName[i]+"\t\t"+binfo.times[i]+"\t"+(binfo.isBorrowed[i]>0?"未借出":"已借出"));
                    borrowCount++;
                }else{
                    notBorrowCount++;
                }
            }
        }
        if(notBorrowCount==savecount){
            System.out.println("图书馆内为向外借出书籍");
        }
        if(borrowCount!=0){
            System.out.println("本馆一共借出了["+borrowCount+"]本书");
        }
    }
    public void findAllState0(){
        int savecount=checkBook();
        int borrowCount=0;
        int notBorrowCount=0;
        if (savecount==0){
            System.out.println("暂时没有存放书籍");
            return;
        }
        System.out.println("序号\t书名\t\t借出次数\t借出状态\t");
        for(int i=0;i<binfo.bName.length;i++){
            if(binfo.bName[i]!=null){
                if(binfo.isBorrowed[i]==0){
                    System.out.println((i+1)+"\t"+binfo.bName[i]+"\t\t"+binfo.times[i]+"\t"+(binfo.isBorrowed[i]>0?"未借出":"已借出"));
                    notBorrowCount++;
                }else{
                    borrowCount++;
                }
            }
        }
        if(borrowCount==savecount){
            System.out.println("图书馆内所有书籍都已借出");
        }
        if(notBorrowCount>0){
            System.out.println("本馆一共有["+notBorrowCount+"]本书未借出");
        }
    }
}
