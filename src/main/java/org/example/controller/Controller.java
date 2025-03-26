package org.example.controller;
import org.example.dto.Member;

public class Controller {

    protected static Member loginedMember;

    public void doAction(String cmd, String actionMethodName) {
        // 구현하지 마세요
    }

    /**로그인되었다면 로그인 된 멤버 반환, 로그인되지 않았다면 null 반환하는 메서드**/
    protected boolean isLogined() {
        return loginedMember != null;
    }
}
