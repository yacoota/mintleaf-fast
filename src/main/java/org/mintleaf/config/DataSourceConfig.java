package org.mintleaf.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.ibeetl.starter.BeetlSqlMutipleSourceCustomize;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.SQLException;

/**
 * @Author: MengchuZhang
 * @Date: 2018/8/3 16:04
 * @Version 1.0
 */

@Configuration
public class DataSourceConfig {
	@Bean(name = "core")
	public DataSource zmc(Environment env) {
		DruidDataSource ds =new DruidDataSource();
		ds.setUrl(env.getProperty("spring.core.url"));
		ds.setUsername(env.getProperty("spring.core.username"));
		ds.setPassword(env.getProperty("spring.core.password"));
		ds.setDriverClassName(env.getProperty("spring.core.driver-class-name"));
		//configuration
		ds.setInitialSize(Integer.valueOf(env.getProperty("spring.core.initialSize")));
		ds.setMinIdle(Integer.valueOf(env.getProperty("spring.core.minIdle")));
		ds.setMaxActive(Integer.valueOf(env.getProperty("spring.core.maxActive")));
		ds.setMaxWait(Integer.valueOf(env.getProperty("spring.core.maxWait")));
		ds.setTimeBetweenEvictionRunsMillis(Integer.valueOf(env.getProperty("spring.core.timeBetweenEvictionRunsMillis")));
		ds.setMinEvictableIdleTimeMillis(Integer.valueOf(env.getProperty("spring.core.minEvictableIdleTimeMillis")));
		ds.setValidationQuery(env.getProperty("spring.core.validationQuery"));
		ds.setTestWhileIdle(Boolean.valueOf(env.getProperty("spring.core.testWhileIdle")));
		ds.setTestOnBorrow(Boolean.valueOf(env.getProperty("spring.core.testOnBorrow")));
		ds.setTestOnReturn(Boolean.valueOf(env.getProperty("spring.core.testOnReturn")));
		ds.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("spring.core.poolPreparedStatements")));
		ds.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(env.getProperty("spring.core.maxPoolPreparedStatementPerConnectionSize")));
		try {
			ds.setFilters(env.getProperty("spring.core.filters"));
		} catch (SQLException e) {
			System.err.println("druid configuration initialization filter: " + e);
		}
		ds.setConnectionProperties(env.getProperty("spring.core.connectionProperties"));
		ds.setUseGlobalDataSourceStat(Boolean.valueOf(env.getProperty("spring.core.useGlobalDataSourceStat")));

		return ds;
	}




}