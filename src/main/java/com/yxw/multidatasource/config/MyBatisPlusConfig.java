package com.yxw.multidatasource.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyBatisPlusConfig {

    @Value("${spring.datasource.dynamic.datasource.mysql.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.dynamic.datasource.mysql.username}")
    private String mysqlUsername;

    @Value("${spring.datasource.dynamic.datasource.mysql.password}")
    private String mysqlPassword;

    @Value("${spring.datasource.dynamic.datasource.mysql.driver-class-name}")
    private String mysqlDriverClassName;


    @Value("${spring.datasource.dynamic.datasource.postgresql.url}")
    private String postgreUrl;

    @Value("${spring.datasource.dynamic.datasource.postgresql.username}")
    private String postgreUsername;

    @Value("${spring.datasource.dynamic.datasource.postgresql.password}")
    private String postgrePassword;

    @Value("${spring.datasource.dynamic.datasource.postgresql.driver-class-name}")
    private String postgreDriverClassName;

    @Bean
    public DataSource mysqlDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(mysqlDriverClassName);
        dataSource.setJdbcUrl(mysqlUrl);
        dataSource.setUsername(mysqlUsername);
        dataSource.setPassword(mysqlPassword);
        return dataSource;
    }

    @Bean
    public DataSource postgreDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(postgreDriverClassName);
        dataSource.setJdbcUrl(postgreUrl);
        dataSource.setUsername(postgreUsername);
        dataSource.setPassword(postgrePassword);
        return dataSource;
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource(@Qualifier("mysqlDataSource") DataSource mysqlDataSource,
                                        @Qualifier("postgreDataSource") DataSource postgreDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("mysql", mysqlDataSource);
        targetDataSources.put("postgresql", postgreDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(mysqlDataSource);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/*.xml")
        );
        factoryBean.setTypeAliasesPackage("com.yxw.multidatasource.entity");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        return factoryBean.getObject();
    }
}
