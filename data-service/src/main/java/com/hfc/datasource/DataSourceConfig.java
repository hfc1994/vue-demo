package com.hfc.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
@Configuration
@MapperScan(basePackages = "com.hfc.dbOperations", sqlSessionFactoryRef = "doubanSqlSessionFactory" )
public class DataSourceConfig
{
    @Bean(name = "doubanDataSource")
    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource doubanDataSource()
    {
        return DruidDataSourceBuilder.create().build();
//        return DataSourceBuilder.create().build();
    }

    /**
     * session工厂
     */
    @Bean(name = "doubanSqlSessionFactory")
    public SqlSessionFactory doubanSqlSessionFactory(@Qualifier("doubanDataSource") DataSource dataSource)
            throws Exception
    {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 事务管理
     */
    @Bean(name = "doubanTransactionManager")
    public DataSourceTransactionManager doubanTransactionManager(@Qualifier("doubanDataSource") DataSource dataSource)
    {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "doubanSqlSessionTemplate")
    public SqlSessionTemplate doubanSqlSessionTemplate(@Qualifier("doubanSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception
    {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
