package org.example.controller;
import org.example.dto.Member;

public class Controller {

    protected static Member loginedMember;
    protected static String writerLoginId;

    public void doAction(String cmd, String actionMethodName) {
        // 구현하지 마세요
    }

    /**로그인 여부 반환 메서드**/
    public static boolean isLogined() {
        return loginedMember != null;
    }

    /**로그인된 member의 Id 반환하는 메서드**/
    protected static String getLoginMemberId() {
        return loginedMember.getLoginId();
    }
}
