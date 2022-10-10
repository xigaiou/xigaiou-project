package com.xigaiou.xigaiouproject;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;



@SpringBootTest
public class JdbcTests {

	//DI注入数据源
	@Autowired
	DataSource dataSource;

	@Test
	public void contextLoads() throws SQLException {
		//看一下默认数据源
		System.out.println("!!!!!!!!"+dataSource.getClass());
		//获得连接
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		//关闭连接
		connection.close();
	}
}