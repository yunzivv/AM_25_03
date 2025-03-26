package org.example.articleManager;

import org.example.controller.ArticleController;
import org.example.controller.MemberController;
import org.example.controller.Controller;

import java.util.Scanner;

public class App {

    public void run() {

        Scanner sc = new Scanner(System.in);

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        Controller controller = null;

        // 테스트 데이터 추가
        articleController.makeTestData();
        memberController.makeTestData();

        System.out.println("============ 프로그램 시작 ============");

        while (true) {

            System.out.print("cmd : ");
            String cmd = sc.nextLine().trim();
            System.out.println("---------------------------------------");

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;

            } else if (cmd.equals("exit")) {
                System.out.println("Goodbye!");
                System.out.println("============ 프로그램 종료 ============");
                break;

            }

            String[] cmdArr = cmd.split(" ");

            if (cmdArr.length == 1) {
                System.out.println("잘못된 명령어 입니다.");
                System.out.println("---------------------------------------\n");
                continue;
            }

            String cmdCheck = cmdArr[0] + " " + cmdArr[1];

            // 로그인 후 사용 가능한 명령어
            switch (cmdCheck) {
                case "article write":
                case "article delete":
                case "article modify":
                case "member logout":
                    if (!Controller.isLogined()) {
                        System.out.println("권한이 없습니다. 로그인해주세요.\n");
                        continue;
                    }
            }

            // 로그아웃 후 사용 가능한 명령어
            switch (cmdCheck) {
                case "member join":
                case "member login":
                    if (Controller.isLogined()) {
                        System.out.println("로그아웃 후 사용 가능합니다.\n");
                        continue;
                    }
            }

            String controllerName = cmdArr[0];
            String actionMethodName = cmdArr[1];

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("지원하지 않는 기능입니다.");
                System.out.println("---------------------------------------\n");
                continue;
            }

            controller.doAction(cmd, actionMethodName);

        }
        sc.close();
    }
}
