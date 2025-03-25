package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Article article;
        List<Article> articles = new ArrayList<>();
        int lastNum = 1;

        System.out.println("프로그램 시작");

        while (true) {
            System.out.print("cmd : ");
            String cmd = sc.nextLine();
            System.out.println("---------------------------------------");

            if (cmd.equals("exit")) {
                System.out.println("Goodbye!");
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
                    System.out.println("============== No articles ==============\n");
                    continue;
                }
                System.out.println("=======================================");
                for (int i = articles.size() - 1; i >= 0; i--) {

                    if(Util.getNow().split(" ")[0].equals(articles.get(i).rgDate.split(" ")[0])) {
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
                    System.out.printf("시간 : %s\n", articles.get(detailNo - 1).rgDate);
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
}