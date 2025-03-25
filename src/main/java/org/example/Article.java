package org.example;

public class Article {

    int num;
    String title;
    String content;
    String rgDate;
    String upDate;

    Article(int num, String rgdate, String update, String title, String content){
        this.num = num;
        this.rgDate = rgdate;
        this.upDate = update;
        this.title = title;
        this.content = content;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRgDate() {
        return rgDate;
    }

    public void setRgDate(String rgDate) {
        this.rgDate = rgDate;
    }

    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate;
    }

}
