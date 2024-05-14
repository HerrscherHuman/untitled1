package com.hsm240513.Until;

public class Result {
    private int state;
    private String msg;
    private Object Data1;
    private Object Data2;
    public int getState(){
        return state;
    }
    public void setState(int state){
        this.state=state;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg=msg;
    }
    public Object getData1(){
        return Data1;
    }
    public void setData1(Object Data1){
        Data1=Data1;
    }
    public Object getData2(){
        return Data2;
    }
    public void setData2(Object Data2){
        Data2=Data2;
    }
}
