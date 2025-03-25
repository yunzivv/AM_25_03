package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Article {

    int num;
    String title;
    String content;
    String rgDate;

    Article(int num, String rgdate, String title, String content){
        this.num = num;
        this.rgDate = rgdate;
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

}
