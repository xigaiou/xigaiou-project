package com.xigaiou.xigaiouproject.application.api;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DruidApi {

    //DI注入数据源
    @Autowired
    DataSource dataSource;

    public void contextLoads() throws SQLException {
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;



        //关闭连接
        //connection.close();
    }
}