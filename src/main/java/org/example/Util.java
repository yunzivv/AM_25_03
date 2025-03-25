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
}
