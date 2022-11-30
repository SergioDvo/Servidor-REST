package dao;

import JDBC.DBConnectionPool;
import dao.querysConstant.SQLQueries;
import domain.errores.ConnectionDBException;
import jakarta.inject.Inject;
import dao.modelo.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DaoArticle {

    private final DBConnectionPool pool;

    @Inject
    public DaoArticle(DBConnectionPool pool) {
        this.pool = pool;
    }

    public List<Article> getAll() {
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            return jtm.query(SQLQueries.SELECT_ALL_ARTICLES, BeanPropertyRowMapper.newInstance(Article.class));
        } catch (Exception e) {
            throw new ConnectionDBException(e.getMessage());
        }

    }

    public void save(Article article) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(pool.getDataSource()).withTableName("article")
                    .usingGeneratedKeyColumns("id");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name_article", article.getName_article());
            parameters.put("id_type", article.getId_type());
            parameters.put("id_newspaper", article.getId_newspaper());
            article.setId((int) jdbcInsert.executeAndReturnKey(parameters).longValue());
        } catch (Exception e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }

}
