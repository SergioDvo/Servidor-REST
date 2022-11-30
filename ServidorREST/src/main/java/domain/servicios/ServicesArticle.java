package domain.servicios;

import dao.DaoArticle;
import dao.modelo.Article;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class ServicesArticle {

    private final DaoArticle daoArticle;

    @Inject
    public ServicesArticle(DaoArticle daoArticle) {
        this.daoArticle = daoArticle;
    }

    public List<Article> getArticleList() {
        return daoArticle.getAll();
    }

    public void addArticle(Article article) {
        daoArticle.save(article);
    }

    public List<Article> getArticleListByType(int type) {
        List<Article> articleList = getArticleList();
        articleList.removeIf(article -> article.getId_type() != type);
        return articleList;
    }

}
