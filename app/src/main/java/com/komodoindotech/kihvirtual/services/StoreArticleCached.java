package com.komodoindotech.kihvirtual.services;

import com.alibaba.fastjson.JSON;
import com.komodoindotech.kihvirtual.json.ArticleObject;
import com.komodoindotech.kihvirtual.models.Article;
import com.komodoindotech.kihvirtual.repositories.ArticleRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StoreArticleCached {
    public static void handler(ArticleRepository articleRepository, List<ArticleObject> articleObjects){
        Article article = new Article();

        Date current_date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        String datajson = JSON.toJSONString(articleObjects);

        article.created_at = current_date.getTime();
        article.expired_at = calendar.getTime().getTime();
        article.updated_at = current_date.getTime();
        article.json = datajson;
        articleRepository.insert(article);
    }
}
