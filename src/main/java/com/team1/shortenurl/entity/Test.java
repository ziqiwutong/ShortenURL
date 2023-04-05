package com.team1.shortenurl.entity;

public class Test {
    private String name;
    private int uid;
    private boolean status;
    private String time;
    private Long num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                ", time='" + time + '\'' +
                ", num=" + num +
                '}';
    }
}
