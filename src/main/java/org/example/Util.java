package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
}
