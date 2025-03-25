package org.example;


public class Member {

    private int id;
    private String regDate;
    private String loginId;
    private String loginPw;
    private String name;
    static int lastId = 1;

    Member(String regDate, String loginId, String loginPwd, String name) {
        this.id = lastId++;
        this.regDate = regDate;
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

    public String getLoginPwd() {
        return loginPw;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPw = loginPwd;
    }

    public boolean isLogin(Member member, String loginId, String loginPwd) {

        if(member.getLoginId().equals(loginId)) {
            if(member.getLoginPwd().equals(loginPwd)) {
                return true;
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
                return false;
            }
        }
        return false;
    }

}
