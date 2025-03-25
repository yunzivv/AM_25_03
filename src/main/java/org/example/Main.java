package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Util.getNow;
import static org.example.Util.listForDate;


class Main {

    // static은 static 끼리만 통신 가능하기 때문에
    static List<Article> articles = new ArrayList<>();
    static List<Member> members = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Article article;
        int lastNum = 1;
        String loginId;
        String loginPassword = null;
        String name;
        boolean login;

        // 테스트 데이터 추가
        lastNum = makeTestDataArticle(lastNum, articles);
        makeTestDataMember(members);

        System.out.println("============ 프로그램 시작 ============");
        while (true) {

            System.out.print("cmd : ");
            // 공백이 남아 있어서 명령어 입력 시 오류가 계속 발생
            // 명령어를 trim으로 공백 제거해서 오류 해결
            String cmd = sc.nextLine().trim();
            System.out.println("---------------------------------------");

            if (cmd.equals("exit")) {
                System.out.println("Goodbye!");
                System.out.println("============ 프로그램 종료 ============");
                break;

            } else if (cmd.equals("join")) {

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

            } else if (cmd.equals("login")) {

                System.out.println("[ 로그인 ]");
                System.out.print("ID : ");
                String ID = sc.nextLine().trim();
                System.out.print("Password : ");
                String PW = sc.nextLine().trim();

                // 입력한 ID와 PW가 있다면 로그인 성공
                login = false;
                for (Member member : members) {
                    String loginResult = member.isLogin(member, ID, PW);
                    if (loginResult.equals("TT")) {
                        System.out.println("로그인 되었습니다.");
                        System.out.println("---------------------------------------\n");
                        login = true;
                        break;
                    } else if (loginResult.equals("TF")) {
                        System.out.println("잘못된 비밀번호 입니다.");
                        System.out.println("---------------------------------------\n");
                        break;
                    } else {
                        System.out.println("존재하지 않는 아이디입니다.");
                        System.out.println("---------------------------------------\n");
                        break;
                    }
                }


            } else if (cmd.equals("logout")) {

                // 로그아웃은 ID/PW가 필요 없을 것 같다.
//                System.out.println("[ 로그아웃 ]");
//                System.out.print("ID : ");
//                loginId = sc.next();
//                System.out.print("Password : ");
//                loginPassword = sc.next();
//
//                boolean checkLogin = false;
//                for (Member member : members) {
//                    if (member.isLogin(member, loginId, loginPassword)) {
//                        System.out.println("로그아웃 되었습니다.");
//                        checkLogin = true;
//                        break;
//                    }
//                }
//                if (!checkLogin) {
//                    System.out.println("잘못된 ID/PW 입니다.");
//                }
                System.out.println("[ 로그아웃 ]");
                System.out.println("---------------------------------------\n");

            } else if (cmd.equals("article write")) {

                System.out.print("Enter title: ");
                String title = sc.nextLine().trim();
                System.out.print("Enter content: ");
                String content = sc.nextLine().trim();
                String rgDate = Util.getNow();
                String upDate = Util.getNow();
                article = new Article(lastNum, rgDate, upDate, title, content);
                articles.add(article);
                System.out.println(lastNum + "번 article 추가 성공");
                System.out.println("---------------------------------------\n");
                lastNum++;

            } else if (cmd.contains("article list")) {

                System.out.println("No. |     date     |  Title  |  Content");
                if (articles.isEmpty()) {
                    System.out.println("-------------- No articles --------------\n");
                    continue;
                }
                System.out.println("---------------------------------------");

                // 만약 검색할 문자가 있다면 실행
                if (cmd.length() > 12) {

                    for (int i = articles.size() - 1; i >= 0; i--) {
                        // 검색어가 포함된 article만 출력
                        if (Util.findTitle(articles.get(i), cmd.substring(12).strip())) {
                            listForDate(articles.get(i));
                        }
                    }
                    System.out.println("---------------------------------------\n");

                    // 검색할 문자가 없다면 실행
                } else {
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        listForDate(articles.get(i));
                    }
                    System.out.println("---------------------------------------\n");
                }

            } else if (cmd.contains("article detail")) {

                int detailNo = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArti = Util.findArticle(detailNo);

                if (foundArti != null) {
                    System.out.printf("번호 : %d\n", articles.get(detailNo - 1).num);
                    System.out.printf("작성 시간 : %s\n", articles.get(detailNo - 1).rgDate);
                    System.out.printf("수정 시간 : %s\n", articles.get(detailNo - 1).upDate);
                    System.out.printf("제목 : %s\n", articles.get(detailNo - 1).title);
                    System.out.printf("내용 : %s\n", articles.get(detailNo - 1).content);
                    System.out.println("---------------------------------------\n");
                }

            } else if (cmd.contains("article delete")) {

                int deleteNo = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArti = Util.findArticle(deleteNo);

                if (foundArti != null) {
                    articles.remove(foundArti);
                    System.out.println(deleteNo + "번 게시글이 삭제되었습니다.");
                    System.out.println("---------------------------------------\n");
                }


            } else if (cmd.contains("article modify")) {

                int modifyNum = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArti = Util.findArticle(modifyNum);

                if (foundArti != null) {
                    System.out.printf("기존 제목 : %s\n", foundArti.title);
                    System.out.print("새로운 제목 : ");
                    String newTitle = sc.nextLine().trim();
                    foundArti.setTitle(newTitle);

                    System.out.printf("기존 내용 : %s\n", foundArti.content);
                    System.out.print("새로운 내용 : ");
                    String newContent = sc.nextLine().trim();
                    foundArti.setContent(newContent);

                    String upDate = Util.getNow();
                    foundArti.setUpDate(upDate);

                    System.out.println(modifyNum + "번 게시글 수정합니다.");
                    System.out.println("---------------------------------------\n");
                }
            } else {
                System.out.println("잘못된 명령어입니다.");
                System.out.println("---------------------------------------\n");
            }
        }

    }

    /**
     * article 테스트 데이터 메서드
     **/
    public static int makeTestDataArticle(int lastId, List<Article> articles) {
        System.out.println("(테스트 데이터 article 3EA 추가)");
        articles.add(new Article(lastId++, "2024-10-10 01:01:01", "2025-11-11 11:11:11", "keroro", "kerokero"));
        articles.add(new Article(lastId++, "2025-02-02 02:02:02", "2025-12-12 12:12:12", "kululu", "kukuku"));
        articles.add(new Article(lastId++, "2025-03-03 03:03:03", "2025-03-13 13:13:13", "dororo", "ninza"));
        return lastId;
    }

    /**
     * member 테스트 데이터 메서드
     **/
    public static void makeTestDataMember(List<Member> members) {
        System.out.println("(테스트 데이터 Member 3EA 추가)");
        members.add(new Member("2022-02-02", "keroro", "green", "케로로"));
        members.add(new Member("2023-03-03", "kururu", "yellow", "쿠루루"));
        members.add(new Member("2024-04-04", "dororo", "blue", "도로로"));
    }
}