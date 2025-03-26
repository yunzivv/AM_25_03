package org.example.dao;

import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    public List<Article> articles;

    public ArticleDAO() {
        articles = new ArrayList<Article>();
    }
}
