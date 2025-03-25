package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Main {

    // static은 static 끼리만 통신 가능하기 때문에
    static List<Article> articles = new ArrayList<>();

    public static void main(String[] args) {
        int lastNum = 1;

        Scanner sc = new Scanner(System.in);
        Article article;

        System.out.println("============ 프로그램 시작 ============");

        // 테스트 데이터 추가
        lastNum = makeTestData(lastNum, articles);

        while (true) {
            System.out.print("cmd : ");
            String cmd = sc.nextLine();
            System.out.println("---------------------------------------");

            if (cmd.equals("exit")) {
                System.out.println("Goodbye!");
                System.out.println("============ 프로그램 종료 ============");
                break;

            } else if (cmd.equals("article write")) {

                System.out.print("Enter title: ");
                String title = sc.nextLine();
                System.out.print("Enter content: ");
                String content = sc.nextLine();
                String rgDate = Util.getNow();
                String upDate = Util.getNow();
                article = new Article(lastNum, rgDate, upDate, title, content);
                articles.add(article);
                System.out.println(lastNum + "번 article 추가 성공");
                System.out.println("---------------------------------------\n");
                lastNum++;

            } else if (cmd.equals("article list")) {

                System.out.println("No. |     date     |  Title  |  Content");
                if (articles.isEmpty()) {
                    System.out.println("-------------- No articles --------------\n");
                    continue;
                }
                System.out.println("---------------------------------------");

                for (int i = articles.size() - 1; i >= 0; i--) {

                    if (Util.getNow().split(" ")[0].equals(articles.get(i).rgDate.split(" ")[0])) {
                        System.out.printf(" %d. |  %-10s  |  %-5s  |  %s\n", articles.get(i).num,
                                articles.get(i).rgDate.split(" ")[1], articles.get(i).title, articles.get(i).content);
                    } else {
                        System.out.printf(" %d. |  %-10s  |  %-5s  |  %s\n", articles.get(i).num,
                                articles.get(i).rgDate.split(" ")[0], articles.get(i).title, articles.get(i).content);
                    }
                }
                System.out.println("---------------------------------------\n");

            } else if (cmd.contains("article detail")) {
                Article foundArti = null;
                int detailNo = Integer.parseInt(cmd.split(" ")[2]);
                for (Article arti : articles) {
                    if (arti.num == detailNo) {
                        foundArti = arti;
                        break;
                    }
                }

                if (foundArti == null) {
                    System.out.println(detailNo + "번 게시글은 없습니다.");
                    System.out.println("-------------------------\n");
                } else {
                    System.out.printf("번호 : %d\n", articles.get(detailNo - 1).num);
                    System.out.printf("작성 시간 : %s\n", articles.get(detailNo - 1).rgDate);
                    System.out.printf("수정 시간 : %s\n", articles.get(detailNo - 1).upDate);
                    System.out.printf("제목 : %s\n", articles.get(detailNo - 1).title);
                    System.out.printf("내용 : %s\n", articles.get(detailNo - 1).content);
                    System.out.println("-------------------------\n");
                }

            } else if (cmd.contains("article delete")) {

                int deleteNo = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArti = null;

                for (Article arti : articles) {
                    if (arti.num == deleteNo) {
                        foundArti = arti;
                        break;
                    }
                }

                if (foundArti == null) {
                    System.out.println(deleteNo + "번 게시글은 없습니다.");
                    System.out.println("-------------------------\n");
                } else {
                    articles.remove(foundArti);
                    System.out.println(deleteNo + "번 게시글이 삭제되었습니다.");
                    System.out.println("-------------------------\n");
                }


            } else if (cmd.contains("article modify")) {

                int modifyNum = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArti = null;

                for (Article arti : articles) {
                    if (arti.num == modifyNum) {
                        foundArti = arti;
                        break;
                    }
                }

                if (foundArti == null) {
                    System.out.println(modifyNum + "번 게시글은 없습니다.");
                    System.out.println("-------------------------\n");
                } else {
                    System.out.printf("기존 제목 : %s\n", foundArti.title);
                    System.out.print("새로운 제목 : ");
                    String newTitle = sc.nextLine();
                    foundArti.setTitle(newTitle);

                    System.out.printf("기존 내용 : %s\n", foundArti.content);
                    System.out.print("새로운 내용 : ");
                    String newContent = sc.nextLine();
                    foundArti.setContent(newContent);

                    String upDate = Util.getNow();
                    foundArti.setUpDate(upDate);

                    System.out.println(modifyNum + "번 게시글 수정합니다.");
                    System.out.println("-------------------------\n");
                }
            } else {
                System.out.println("잘못된 명령어입니다.");
                System.out.println("-------------------------\n");
            }
        }

    }

    /** 테스트 데이터 메서드 **/
    public static int makeTestData(int lastNum, List<Article> articles) {
        articles.add(new Article(lastNum++, "2024-10-10 01:01:01", "2025-11-11 11:11:11", "케로로", "케로케로"));
        articles.add(new Article(lastNum++, "2025-02-02 02:02:02", "2025-12-12 12:12:12", "쿠루루", "쿠쿠쿠"));
        articles.add(new Article(lastNum++, "2025-03-03 03:03:03", "2025-03-13 13:13:13", "도로로", "닌자"));
        return lastNum;
    }
}