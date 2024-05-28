package com.itwill.lab05.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.lab05.datasource.DataSourceUtil;
import com.zaxxer.hikari.HikariDataSource;

// MVC 아키텍쳐에서 영속성 계층(repository layout)을 담당하는 클래스.
// DB에서 CRUD(Create, Read, Update, Delete) 작업을 담당.
// DAO(Data Access Object).
public enum PostDao {
    INSTANCE; // = public static final PostDao INSTANCE = new PostDao();

    private static final Logger log = LoggerFactory.getLogger(PostDao.class);
    private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();

    public List<Post> select() {
        log.debug("select()");

        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}