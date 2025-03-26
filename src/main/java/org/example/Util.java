package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



// 자주 사용할 것 같은 기능 모음 클래스
public class Util {

    /**현재 날짜 반환 메서드**/
    public static String getNow() {
        LocalDateTime now = LocalDateTime.now();
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
    }

    /**작성일자에 따라 다른 article list를 출력하는 메서드**/
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

    /**글 작성 loginId와 login한 Id가 같은 지 확인하는 메서드**/
    public static boolean checkID (String id) {

        return false;
    }
}
