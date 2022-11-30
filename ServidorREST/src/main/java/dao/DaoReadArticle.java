package dao;

import JDBC.DBConnectionPool;
import dao.querysConstant.SQLQueries;
import domain.errores.DataFailureException;
import jakarta.inject.Inject;

import java.sql.*;


public class DaoReadArticle {

    private final DBConnectionPool pool;

    @Inject
    public DaoReadArticle(DBConnectionPool pool) {
        this.pool = pool;
    }

    public void saveReadArticle(int readerId, int articleId, Integer rating) {

        try (Connection con = pool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_READ_ARTICLE)) {
            preparedStatement.setInt(1, articleId);
            preparedStatement.setInt(2, readerId);
            preparedStatement.setInt(3, rating);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataFailureException("Error al guardar el artículo");
        }

    }
}
