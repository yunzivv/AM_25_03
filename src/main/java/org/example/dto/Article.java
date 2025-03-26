package org.example.dto;

public class Article extends DTO {

    private String title;
    private String content;
    private String upDate;
    private int memberId;

    private Member member;

    public Article(int id, String rgdate, String update, int memberId, String title, String content) {
        this.id = id;
        this.rgDate = rgdate;
        this.upDate = update;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(String loginId) {
        this.memberId = memberId;
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
