package org.example.articleManager;

import org.example.dao.ArticleDAO;
import org.example.dao.MemberDAO;

public class Container {

    public static ArticleDAO articleDAO;
    public static MemberDAO memberDAO;

    static {
        articleDAO = new ArticleDAO();
        memberDAO = new MemberDAO();
    }

}
