package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController {

    Scanner sc;
    List<Member> members;
    int lastNum;
    String loginPassword = null;
    String name;
    boolean login = false;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }


    public void join() {

        String loginId;
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
            loginPassword = sc.next().trim();
            System.out.print("Password check : ");
            String loginPasswordCheck = sc.next().trim();

            if (loginPassword.equals(loginPasswordCheck)) {
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다.");
            }
        }

        System.out.print("Name : ");
        // 버퍼에 공백이 남는 오류(왜?) 해결
        sc.nextLine();
        name = sc.nextLine().trim();

        members.add(new Member(Util.getNow(), loginId, loginPassword, name));
        System.out.println("회원가입 완료");
        System.out.println("---------------------------------------\n");

    }

    public void login() {
        System.out.println("[ 로그인 ]");
        System.out.print("ID : ");
        String ID = sc.nextLine().trim();
        System.out.print("Password : ");
        String PW = sc.nextLine().trim();

        Member loginmember = null;

        // 입력한 ID와 PW가 있다면 로그인 성공
        for (Member member : members) {

            if (member.getLoginId().equals(ID)) {
                loginmember = member;

                if (loginmember.getLoginPw().equals(PW)) {
                    System.out.println("로그인 성공");
                    System.out.println("---------------------------------------\n");
                    return;
                } else {
                    System.out.println("비밀번호가 틀렸습니다.");
                    System.out.println("---------------------------------------\n");
                    return;
                }
            }
        }
        if (loginmember == null) {
            System.out.println("잘못된 아이디입니다.");
            System.out.println("---------------------------------------\n");
        }
    }

    public static void logout() {
        System.out.println("[ 로그아웃 ]");
        System.out.println("---------------------------------------\n");
    }

    /**
     * member 테스트 데이터 메서드
     **/
    void makeTestData() {
        System.out.println("(테스트 데이터 Member 3EA 추가)");
        members.add(new Member("2022-02-02", "keroro", "green", "케로로"));
        members.add(new Member("2023-03-03", "kururu", "yellow", "쿠루루"));
        members.add(new Member("2024-04-04", "dororo", "blue", "도로로"));
    }
}
