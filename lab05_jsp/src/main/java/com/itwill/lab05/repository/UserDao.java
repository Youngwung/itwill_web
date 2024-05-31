package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

public enum UserDao {
  INSTANCE;

  // DAO(Data Access Object). 데이터베이스 CRUD.
  private static final Logger log = LoggerFactory.getLogger(UserDao.class);

  private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();

  // users 테이블에 insert를 하는 SQL 문장 & 메서드 구현.
  private final String SQL_INSERT = "insert into users "
      + "(userid, password, email) "
      + "values (?, ?, ?)";

  public int create(User user) {
    log.debug("{}", user);
    int result = 0;
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ds.getConnection();
      stmt = conn.prepareStatement(SQL_INSERT);
      stmt.setString(1, user.getUserid());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getEmail());
      result = stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeResources(conn, stmt);
    }

    return result;
  }

  private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
    try {
      if (rs != null)
        rs.close();
      if (stmt != null)
        stmt.close();
      if (conn != null)
        conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void closeResources(Connection conn, PreparedStatement stmt) {
    closeResources(conn, stmt, null);
  }
}
