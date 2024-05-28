package com.itwill.lab05.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceTest {
	private static final Logger log = LoggerFactory.getLogger(HikariDataSourceTest.class);

	private final DataSourceUtil util = DataSourceUtil.getInstance();

	// 테스트 클래스에는 실행할 메서드에 반드시 애노테이션을 붙여야함.
	@Test
	public void test() throws SQLException {
		HikariDataSource ds = util.getDataSource();
		Assertions.assertNotNull(ds); // ! ds는 null이 아닌가?

		log.debug("ds = {}", ds);
		// System.out.printf("ds = %s", ds); =>얘보다 좋은게 변수 타입이 상관없음

		Connection conn = ds.getConnection(); // 커넥션 풀에서 커넥션 객체를 빌려옴
		Assertions.assertNotNull(conn); // ! conn은 null이 아닌가?
		log.debug("conn = {}", conn);

		conn.close(); // 사용했던 커넥션(연결)을 커넥션 풀에 반환.
		log.debug("CP closed");

	}
}
