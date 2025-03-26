package org.example.controller;

import org.example.dto.Article;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.util.Util.listForDate;

public class ArticleController extends Controller {

    Scanner sc;
    List<Article> articles;
    int lastNum = 4;
    String cmd;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articles = new ArrayList<Article>();
    }

    public void doAction(String cmd, String actionMethodName) {

        this.cmd = cmd;

        switch (actionMethodName) {
            case "write":
                write();
                break;
            case "modify":
                modify();
                break;
            case "delete":
                delete();
                break;
            case "list":
                list();
                break;
            case "detail":
                detail();
                break;
            default:
                System.out.println("Invalid action method");
                break;
        }

    }

    public void write() {
        System.out.print("Enter title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter content: ");
        String content = sc.nextLine().trim();
        String rgDate = Util.getNow();
        String upDate = Util.getNow();
        Article article = new Article(lastNum, rgDate, upDate, title, content);
        articles.add(article);
        System.out.println(lastNum + "번 article 추가 성공");
        System.out.println("---------------------------------------\n");
        lastNum++;
    }

    public void modify() {

        int modifyNo = cmd.split(" ").length - 1;
        Article article = findArticle(modifyNo);

        if (article != null) {
            System.out.printf("기존 제목 : %s\n", article.title);
            System.out.print("새로운 제목 : ");
            String newTitle = sc.nextLine().trim();
            article.setTitle(newTitle);

            System.out.printf("기존 내용 : %s\n", article.content);
            System.out.print("새로운 내용 : ");
            String newContent = sc.nextLine().trim();
            article.setContent(newContent);

            String upDate = Util.getNow();
            article.setUpDate(upDate);

            System.out.println(modifyNo + "번 게시글 수정합니다.");
            System.out.println("---------------------------------------\n");
        }
    }

    public void delete() {

        int deleteNo = cmd.split(" ").length - 1;
        Article article = findArticle(deleteNo);

        if (article != null) {
            articles.remove(article);
            System.out.println(deleteNo + "번 게시글이 삭제되었습니다.");
            System.out.println("---------------------------------------\n");
        }
    }

    public void list() {

        System.out.println("No. |     date     |  Title  |  Content");

        if (articles.isEmpty()) {
            System.out.println("------------- No articles -------------\n");
            return;
        }

        System.out.println("---------------------------------------");
        if (cmd.length() > 12) {
            int findArticleNo = 0;
            for (int i = articles.size() - 1; i >= 0; i--) {
                // 검색어가 포함된 article만 출력
                if (findTitle(articles.get(i), cmd.substring(12).strip())) {
                    listForDate(articles.get(i));
                    findArticleNo++;
                }
            }
            if (findArticleNo == 0) {
                System.out.println("------------- No articles -------------\n");
                return;
            }
        } else {
            for (int i = articles.size() - 1; i >= 0; i--) {
                listForDate(articles.get(i));
            }
        }
        System.out.println("---------------------------------------\n");
    }

    public void detail() {

        int detailNo = cmd.split(" ").length - 1;
        Article article = findArticle(detailNo);

        if (article != null) {
            System.out.printf("번호 : %d\n", articles.get(detailNo - 1).num);
            System.out.printf("작성 시간 : %s\n", articles.get(detailNo - 1).rgDate);
            System.out.printf("수정 시간 : %s\n", articles.get(detailNo - 1).upDate);
            System.out.printf("제목 : %s\n", articles.get(detailNo - 1).title);
            System.out.printf("내용 : %s\n", articles.get(detailNo - 1).content);
            System.out.println("---------------------------------------\n");
        }
    }

    /**
     * article 테스트 데이터 메서드
     **/
    public void makeTestData() {
        System.out.println("(테스트 데이터 article 3EA 추가)");
        articles.add(new Article(1, "2024-10-10 01:01:01", "2025-11-11 11:11:11", "keroro", "kerokero"));
        articles.add(new Article(2, "2025-02-02 02:02:02", "2025-12-12 12:12:12", "kululu", "kukuku"));
        articles.add(new Article(3, "2025-03-03 03:03:03", "2025-03-13 13:13:13", "dororo", "ninza"));
    }

    /**
     * 입력받은 번호의 article  반환 메서드
     **/
    public Article findArticle(int num) {
        for (Article article : articles) {
            if (article.num == num) {
                return article;
            }
        }
        System.out.printf("%d번 article은 없습니다.\n", num);
        System.out.println("---------------------------------------\n");
        return null;
    }

    /**
     * 입력받은 글자를 포함하는 제목을 가진 article  반환 메서드
     **/
    public boolean findTitle(Article article, String find) {
        if (article.title.contains(find)) {
            return true;
        }
        return false;
    }
}
