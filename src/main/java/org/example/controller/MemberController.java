package org.example.controller;

import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private Scanner sc;
    private List<Member> members;
    private String cmd;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            default:
                System.out.println("Invalid action method");
                System.out.println("---------------------------------------\n");
                break;
        }

    }

    private void doJoin() {

        String loginId;
        String loginPw;
        System.out.println("[ 회원가입 ]");
        // 중복되지 않은 ID 입력받기
        while (true) {
            System.out.print("ID : ");
            loginId = sc.nextLine().trim();

            // 중복되는 ID를 발견하면 continue
            boolean checkID = true;
            for (Member member : members) {
                if (member.getLoginId().equals(loginId)) {
                    System.out.println("이미 존재하는 ID 입니다.");
                    checkID = false;
                }
            }
            // 중복되는 ID를 발견하지 못했으면 break
            if (checkID) {
                break;
            }
        }
        // 비밀번호 확인
        while (true) {
            System.out.print("Password : ");
            loginPw = sc.next().trim();
            System.out.print("Password check : ");
            String loginPasswordCheck = sc.next().trim();

            if (loginPw.equals(loginPasswordCheck)) {
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        }

        System.out.print("Name : ");
        // 버퍼에 공백이 남는 오류(왜?) 해결
        sc.nextLine();
        String name = sc.nextLine().trim();

        members.add(new Member(Util.getNow(), loginId, loginPw, name));
        System.out.println("회원가입 완료");
        System.out.println("---------------------------------------\n");

    }

    private void doLogin() {

        if(loginedMember != null) {
            System.out.println("이미 로그인 되었습니다.");
            System.out.println("---------------------------------------\n");
            return;
        }

        System.out.println("[ 로그인 ]");
        System.out.print("Login Id : ");
        String LoginId = sc.nextLine().trim();
        System.out.print("Login Password : ");
        String LofinPw = sc.nextLine().trim();

        // 입력한 ID와 PW가 있다면 로그인 성공
        Member loginMember = null;

        for (Member member : members) {

            if (member.getLoginId().equals(LoginId)) {

                loginMember = member;

                if (loginMember.getLoginPw().equals(LofinPw)) {
                    System.out.printf("%s님 로그인 성공!\n", member.getName());
                    System.out.println("---------------------------------------\n");
                    loginedMember = loginMember;
                    return;
                } else {
                    System.out.println("비밀번호가 틀렸습니다.");
                    System.out.println("---------------------------------------\n");
                    return;
                }
            }
        }
        if (loginMember == null) {
            System.out.println("잘못된 아이디입니다.");
            System.out.println("---------------------------------------\n");
        }
    }

    private void doLogout() {

        if(loginedMember == null) {
            System.out.println("로그인되지 않았습니다.");
            System.out.println("---------------------------------------\n");
            return;
        }

        loginedMember = null;

        System.out.println("[ 로그아웃 ]");
        System.out.println("---------------------------------------\n");
    }

    /**
     * member 테스트 데이터 메서드
     **/
    public void makeTestData() {
        System.out.println("(테스트 데이터 Member 3EA 추가)");
        members.add(new Member("2022-02-02", "keroro", "green", "케로로"));
        members.add(new Member("2023-03-03", "kululu", "yellow", "쿠루루"));
        members.add(new Member("2024-04-04", "dororo", "blue", "도로로"));
    }
}
