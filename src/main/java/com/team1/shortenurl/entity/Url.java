package com.team1.shortenurl.entity;

public class Url {
    private int serviceId;
    private int uid;
    private String longUrl;
    private String shortUrl;
    private int countClick;
    private String createTime;
    private String updateTime;

    @Override
    public String toString() {
        return "Url{" +
                "service_id=" + serviceId +
                ", uid=" + uid +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", count=" + countClick +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getCountClick() {
        return countClick;
    }

    public void setCountClick(int countClick) {
        this.countClick = countClick;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
