package org.example.controller;

import org.example.articleManager.Container;
import org.example.dto.Article;
import org.example.dto.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.util.Util.getNow;

public class ArticleController extends Controller {

    private Scanner sc;
    private List<Article> articles;
    private String cmd;
    private String writerId;

    int lastNum = 4;

    List<Member> members = Container.memberDAO.members;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articles = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {

        this.cmd = cmd;

        switch (actionMethodName) {
            case "write":
                doWrite();
                break;
            case "modify":
                doModify();
                break;
            case "delete":
                doDelete();
                break;
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            default:
                System.out.println("지원하지 않는 기능입니다.");
                System.out.println("----------------------------------------------------\n");
                break;
        }

    }

    private void doWrite() {

        System.out.println("[article write]");

        writerId = getLoginMemberId();

        System.out.print("Enter title: ");
        String title = sc.nextLine().trim();

        System.out.print("Enter content: ");
        String content = sc.nextLine().trim();

        String rgDate = getNow();
        String upDate = getNow();

        // 글 작성 시 로그인된 멤버의 아이디를 같이 저장
        Article article = new Article(lastNum, rgDate, upDate, loginedMember.getId(), title, content);
        articles.add(article);

        System.out.println(lastNum + "번 article이 작성되었습니다.");
        System.out.println("----------------------------------------------------\n");

        lastNum++;
    }

    private void doModify() {

        int modifyNo = Integer.parseInt(cmd.split(" ")[2]);
        Article article = findArticle(modifyNo);

        if (article == null) {
            System.out.printf("article %d은 존재하지 않습니다.", modifyNo);
            return;
        }

        if (article.getMemberId() != loginedMember.getId()) {
            System.out.println("수정 권한이 없습니다.");
            System.out.println("----------------------------------------------------\n");
            return;
        }

        System.out.printf("[article %d modify]\n", modifyNo);

        System.out.printf("기존 제목 : %s\n", article.getTitle());
        System.out.printf("기존 내용 : %s\n", article.getContent());

        System.out.print("새로운 제목 : ");
        String newTitle = sc.nextLine().trim();
        article.setTitle(newTitle);

        System.out.print("새로운 내용 : ");
        String newContent = sc.nextLine().trim();
        article.setContent(newContent);

        String upDate = getNow();
        article.setUpDate(upDate);

        System.out.println(modifyNo + "번 article이 수정되었습니다.");
        System.out.println("----------------------------------------------------\n");

    }

    private void doDelete() {

        int deleteNo = Integer.parseInt(cmd.split(" ")[2]);
        Article article = findArticle(deleteNo);

        if (article == null) {
            System.out.printf("article %d는 존재하지 않습니다.", deleteNo);
            return;
        }

        if (article.getMemberId() != loginedMember.getId()) {
            System.out.println("삭제 권한이 없습니다.");
            System.out.println("----------------------------------------------------\n");
            return;
        }

        System.out.printf("[article %d delete]\n", deleteNo);
        articles.remove(article);
        System.out.println(deleteNo + "번 article이 삭제되었습니다.");
        System.out.println("----------------------------------------------------\n");
    }

    private void showList() {

        System.out.println("No. |     date     |  Title  |  Content  |  Writer  ");

        String keyword = cmd.substring("article list".length()).trim();

        if (articles.isEmpty()) {
            System.out.println("------------------- No articles --------------------\n");
            return;
        }

        List<Article> printAricles = articles;
        if (keyword.length() > 0) {

            printAricles = new ArrayList<>();
            for(Article article : articles) {
                if(article.getTitle().contains(keyword)) {
                    printAricles.add(article);
                }
            }

            if (printAricles.size() == 0) {
                System.out.println("---------------- No result for found -----------------\n");
                return;
            }

            System.out.println("----------------------------------------------------");
            System.out.printf("search : %s  result : %d\n", keyword, printAricles.size());
        }

        System.out.print("----------------------------------------------------\n");
        for (int i = printAricles.size() - 1; i >= 0; i--) {

            Article article = printAricles.get(i);
            String writer = getMemberName(article);

            if(article.getRgDate().split(" ")[1].equals(getNow())) {
                System.out.printf(" %d  |  %10s  |  %5s  |  %7s  |  %5s  \n",
                        article.getId(), article.getRgDate().split(" ")[1],
                        article.getTitle(), article.getContent(), writer);
            } else {
                System.out.printf(" %d  |  %10s  |  %5s  |  %7s  |  %5s  \n",
                        article.getId(), article.getRgDate().split(" ")[0],
                        article.getTitle(), article.getContent(), writer);
            }
        }
        System.out.println("----------------------------------------------------\n");
    }

    private void showDetail() {

        int detailNo = Integer.parseInt(cmd.split(" ")[2]);
        Article article = findArticle(detailNo);

        if (article != null) {
            System.out.printf("번호 : %d\n", article.getId());
            System.out.printf("작성 시간 : %s\n", article.getRgDate());
            System.out.printf("수정 시간 : %s\n", article.getUpDate());
            System.out.printf("작성자 : %s\n", getWriterId(article));
            System.out.printf("제목 : %s\n", article.getTitle());
            System.out.printf("내용 : %s\n", article.getContent());
            System.out.println("----------------------------------------------------\n");
        }
    }

    /**
     * article 테스트 데이터 메서드
     **/
    public void makeTestData() {
        System.out.println("(테스트 데이터 Article 3EA 추가)");
        articles.add(new Article(1, "2024-10-10 01:01:01", "2025-11-11 11:11:11", 1, "keroro", "kerokero"));
        articles.add(new Article(2, "2025-02-02 02:02:02", "2025-12-12 12:12:12", 2, "kululu", "kukuku"));
        articles.add(new Article(3, "2025-03-03 03:03:03", "2025-03-13 13:13:13", 1, "dororo", "ninza"));
    }

    /**
     * 입력받은 번호의 article  반환 메서드
     **/
    private Article findArticle(int num) {
        for (Article article : articles) {
            if (article.getId() == num) {
                return article;
            }
        }
        System.out.printf("article %d는 존재하지 않습니다.\n", num);
        System.out.println("----------------------------------------------------\n");
        return null;
    }

    /**
     * 작성자 Id를 반환하는 메서드
     **/
    private int getWriterId(Article article) {
        return article.getMemberId();
    }

    /**입력받은 article memberid와 같은 id를 가진 멤버의 이름 반환**/
    private String getMemberName(Article article) {
        for (Member member : members) {
            if (member.getLoginId().equals(getWriterId(article))) {
                return member.getName();
            }
        }
        return null;
    }
}
