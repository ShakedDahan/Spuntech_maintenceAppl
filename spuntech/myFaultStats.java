package com.example.spuntech;

public class myFaultStats {

    private String month;
    private String count;
    private String Open;
    private String Close;

    public myFaultStats(String month, String count) {
        this.month = month;
        this.count = count;
    }

    public myFaultStats(String month, String count,String Open,String Close) {
        this.month = month;
        this.count = count;
        this.Close = Close;
        this.Open = Open;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    public String getOpen() {
        return Open;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }


}
