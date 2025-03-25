package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.articles;

// 자주 사용할 것 같은 기능 모음 클래스
public class Util {

    /**현재 날짜 반환 메서드**/
    public static String getNow() {
        LocalDateTime now = LocalDateTime.now();
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
    }

    /**입력받은 번호의 article  반환 메서드**/
    public static Article findArticle(int num) {
        for (Article article : articles) {
            if (article.num == num) {
                return article;
            }
        }
        System.out.printf("%d번 article은 없습니다.\n", num);
        System.out.println("---------------------------------------\n");
        return null;
    }

    /**입력받은 글자를 포함하는 제목을 가진 article  반환 메서드**/
    public static boolean findTitle(Article article, String find) {
        if(article.title.contains(find)) {
            return true;
        }
        return false;
    }

    /** 작성일자에 따라 다른 article list를 출력하는 메서드**/
    public static void listForDate (Article article) {
        if(Util.getNow().split(" ")[0].equals(article.rgDate)) {
            System.out.printf(" %d. | %-12s |  %-5s  |  %s\n",
                    article.num, article.rgDate.split(" ")[1],
                    article.title, article.content);
        } else {
            System.out.printf(" %d. | %-12s |  %-5s  |  %s\n",
                    article.num, article.rgDate.split(" ")[0],
                    article.title, article.content);
        }
    }
}
