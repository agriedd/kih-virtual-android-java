package com.komodoindotech.kihvirtual.json;

import java.util.ArrayList;
import java.util.List;

public class ArticlesObject {
    private List<ArticleObject> articles = new ArrayList<ArticleObject>();

    public void setArticles(List<ArticleObject> articles) {
        this.articles = articles;
    }
    public void addArticle(ArticleObject article) {
        this.articles.add(article);
    }
    public List<ArticleObject> getArticles() {
        return articles;
    }
}
