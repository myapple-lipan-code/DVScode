package com.ljs.callray.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author LiJinSong
 * @date 2021-01-28
 */
@Slf4j
@Configuration
@Primary
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource(){

        DataSource dataSource = new DataSource();
        dataSource.setUrl(dataSourceUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestOnConnect(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(15000);

        try {
            Class.forName(driverClassName);
            String str = dataSourceUrl.substring(0, dataSourceUrl.lastIndexOf("/"));
            String datasourceName = dataSourceUrl.substring(dataSourceUrl.lastIndexOf("/") + 1);
            Connection connection = DriverManager.getConnection(str, username, password);
            Statement statement = connection.createStatement();

            //创建数据库
            statement.executeUpdate("create database if not exists `" + datasourceName + "` default character set utf8 COLLATE utf8_general_ci");
            statement.close();
            connection.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return dataSource;
    }
}
