package dao;

import JDBC.DBConnectionPool;
import dao.modelo.Newspaper;
import dao.querysConstant.SQLQueries;
import domain.errores.ConnectionDBException;
import domain.errores.DataFailureException;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DaoNewspaper {

    private final DBConnectionPool pool;

    @Inject
    public DaoNewspaper(DBConnectionPool pool) {
        this.pool = pool;
    }


    public List<Newspaper> getAll() {
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            return jtm.query(SQLQueries.SELECT_ALL_NEWSPAPERS, BeanPropertyRowMapper.newInstance(Newspaper.class));
        } catch (Exception e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }


    public int save(Newspaper newspaper) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(pool.getDataSource()).withTableName("newspaper")
                    .usingGeneratedKeyColumns("id");

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("name_newspaper", newspaper.getNameNewspaper());
            parameters.put("release_date", newspaper.getReleaseDate());
            newspaper.setId((int) jdbcInsert.executeAndReturnKey(parameters).longValue());
            return 0;
        } catch (Exception e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }
    public void delete(int newspaperId) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(pool.getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jtm = new JdbcTemplate(transactionManager.getDataSource());
            jtm.update(SQLQueries.DELETE_ARTICLE_BY_ID_NEWSPAPER, newspaperId);
            jtm.update(SQLQueries.DELETE_SUBSCRIPTIONS_BY_ID_NEWSPAPER, newspaperId);
            jtm.update(SQLQueries.DELETE_READ_ARTICLE_BY_IDNEWSPAPER, newspaperId);
            jtm.update(SQLQueries.DELETE_NEWSPAPER_BY_ID, newspaperId);
            transactionManager.commit(txStatus);
        } catch (Exception e) {
            transactionManager.rollback(txStatus);
            throw new DataFailureException(e.getMessage());
        }
    }

    public int update(Newspaper newspaper) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            return jtm.update(SQLQueries.UPDATE_NEWSPAPER, newspaper.getNameNewspaper(), newspaper.getReleaseDate(), newspaper.getId());
        } catch (Exception e) {
            throw new DataFailureException(e.getMessage());
        }

    }


}
