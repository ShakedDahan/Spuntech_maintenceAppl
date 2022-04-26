package com.example.spuntech;

public class myEmployeeStats {
    private String employee;
    private int count;
    private int Open;
    private String Close;

    public myEmployeeStats(String employee, int count,int open) {
        this.employee = employee;
        this.count = count;
        this.Open = open;
    }


    public int getOpen() {
        return Open;
    }

    public void setOpen(int open) {
        Open = open;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }
    public String getemployee() {
        return employee;
    }

    public void setemployee(String employee) {
        this.employee = employee;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
