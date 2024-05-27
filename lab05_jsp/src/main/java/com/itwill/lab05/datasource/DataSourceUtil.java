package com.itwill.lab05.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {
    // singleton 구현
    private static DataSourceUtil instance = null;

    private HikariConfig config;
    private static HikariDataSource ds;

    private DataSourceUtil() {
        config = new HikariConfig(); // HikariCP의 설정(configration)을 담당하는 객체 생성.
        // 얘가 db랑 connection연결 도와주는애임

        // 커넥션 풀(데이터소스) 환경 설정.
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        // Contact프로젝트 할때 했던 드라이버 등록
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        // Contact프로젝트 할 때 따로 클래스 만들어서 상수로 등록해놨던 Url
        config.setUsername("jspstudy");
        config.setPassword("jspstudy");
        // ! =============여기까지 필수인 설정인데 추가 설정들이 있을 수 있음=======
        // ex- 커넥션 최대 연결 개수

        // 데이터 소스 객체 생성. // !아규먼트 있는걸로 고르셈.
        ds = new HikariDataSource(config);
    }

    public static DataSourceUtil getInstance() {
        if (instance == null) {
            instance = new DataSourceUtil();
        }
        return instance;
    }

    public HikariDataSource getDataSource() {
        return ds;
    }
    // !얘들 잘 실행되는지 확인하고 싶은데 웹은 메인이 없으므로 실험하기가 힘듬
    // 그래서 해야되는게
}
