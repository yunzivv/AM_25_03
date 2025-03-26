package org.example.dto;


public class Member extends DTO {

    private String loginPw;
    private String name;
    static int lastNum = 1;

    public Member(String regDate, String loginId, String loginPwd, String name) {
        this.id = lastNum++;
        this.rgDate = regDate;
        this.loginId = loginId;
        this.loginPw = loginPwd;
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPwd) {
        this.loginPw = loginPwd;
    }

    public String getName() {return name;}

    public int getId() { return id;
    }
}
