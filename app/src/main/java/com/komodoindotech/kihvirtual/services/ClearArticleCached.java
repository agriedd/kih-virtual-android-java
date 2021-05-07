package com.komodoindotech.kihvirtual.services;

import com.komodoindotech.kihvirtual.repositories.ArticleRepository;

public class ClearArticleCached {
    public static void handler(ArticleRepository articleRepository){
        articleRepository.deleteAll();
    }
}
