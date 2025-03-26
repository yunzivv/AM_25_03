package org.example;

import java.util.Scanner;

import static org.example.Util.listForDate;

public class App {

    public void run() {

        Scanner sc = new Scanner(System.in);

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        // 테스트 데이터 추가
        articleController.makeTestData();
        memberController.makeTestData();

        System.out.println("============ 프로그램 시작 ============");
        while (true) {

            System.out.print("cmd : ");
            String cmd = sc.nextLine().trim();
            System.out.println("---------------------------------------");

            if (cmd.equals("exit")) {
                System.out.println("Goodbye!");
                System.out.println("============ 프로그램 종료 ============");
                break;

            } else if (cmd.equals("join")) {

                memberController.join();

            } else if (cmd.equals("login")) {

                memberController.login();

            } else if (cmd.equals("logout")) {

                MemberController.logout();

            } else if (cmd.equals("article write")) {

                articleController.write();

            } else if (cmd.contains("article list")) {

                articleController.list(cmd);

            } else if (cmd.contains("article detail")) {

                int detailNo = Integer.parseInt(cmd.split(" ")[2]);
                articleController.detail(detailNo);

            } else if (cmd.contains("article delete")) {

                int deleteNo = Integer.parseInt(cmd.split(" ")[2]);
                articleController.delete(deleteNo);

            } else if (cmd.contains("article modify")) {

                int modifyNum = Integer.parseInt(cmd.split(" ")[2]);
                articleController.modify(modifyNum);

            } else {
                System.out.println("잘못된 명령어입니다.");
                System.out.println("---------------------------------------\n");
            }
        }
    }

}
