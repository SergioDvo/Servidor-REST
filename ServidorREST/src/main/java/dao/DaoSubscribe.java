package dao;

import JDBC.DBConnectionPool;
import dao.modelo.Subscription;
import dao.querysConstant.SQLQueries;
import domain.errores.ConnectionDBException;
import domain.errores.DataFailureException;
import jakarta.inject.Inject;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoSubscribe {

    private final DBConnectionPool pool;

    @Inject
    public DaoSubscribe(DBConnectionPool pool) {
        this.pool = pool;
    }


    private List<Subscription> readRS(List<Subscription> readers, ResultSet rs) {
        try {
            while (rs.next()) {
                int id_newspapers = rs.getInt("id_newspaper");
                int id_readers = rs.getInt("id_reader");
                LocalDate signing_date = rs.getDate("signing_date").toLocalDate();
                Subscription reader = new Subscription(id_readers, id_newspapers, signing_date, null);
                readers.add(reader);
            }
            return readers;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Subscription> getAll(int reader) {
        List<Subscription> subscriptions = new ArrayList<>();
        try (Connection con = pool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_SUBSCRIPTIONS_QUERY_BY_ID_READER)) {
            preparedStatement.setInt(1, reader);
            ResultSet rs = preparedStatement.executeQuery();
            return readRS(subscriptions, rs);

        } catch (SQLException ex) {
            throw new DataFailureException(ex.getMessage());
        }
    }

    public void save(Subscription subscription) {
        try (Connection con = pool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_SUBSCRIPTIONS_QUERY)) {
            preparedStatement.setInt(1, subscription.getIdNewspaper());
            preparedStatement.setInt(2, subscription.getIdReader());
            preparedStatement.setDate(3, Date.valueOf(subscription.getSigningDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataFailureException(e.getMessage());
        } catch (Exception e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }

    public void update(Subscription subscription) {
        try (Connection con = pool.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_SUBSCRIPTIONS_QUERY)) {
            preparedStatement.setInt(1, subscription.getIdNewspaper());
            preparedStatement.setDate(2, Date.valueOf(subscription.getSigningDate()));
            preparedStatement.setDate(3, Date.valueOf(subscription.getCancellationDate()));
            preparedStatement.setInt(4, subscription.getIdReader());
            preparedStatement.setInt(5, subscription.getIdNewspaper());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataFailureException(e.getMessage());
        } catch (Exception e) {
            throw new ConnectionDBException(e.getMessage());
        }
    }
}
