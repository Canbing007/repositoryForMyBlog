package com.org.coop.society.data.transaction.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.org.coop.society.data.admin.repositories"}, 
					entityManagerFactoryRef="adminEntityManagerFactory", 
					transactionManagerRef="adminTransactionManager")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class CoOperativeAdminDBConfig {
	@Autowired
	private Environment env;

	@Bean(name = "adminDataSource")
	@Qualifier("adminDataSource")
	public DataSource adminDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.admin.url"));
		dataSource.setUsername(env.getProperty("db.admin.username"));
		dataSource.setPassword(env.getProperty("db.admin.password"));
		return dataSource;
	}

	@Bean(name = "adminEntityManagerFactory")
	@Qualifier("adminEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean adminEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(adminDataSource());
		em.setPackagesToScan(new String[] { "com.org.coop.society.data.admin" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	@Bean(name="adminTransactionManager")
	@Qualifier("adminTransactionManager")
	public PlatformTransactionManager adminTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(adminEntityManagerFactory().getObject());
		return transactionManager;
	}
	

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
//		properties.setProperty("hibernate.use_sql_comments", "true");
		return properties;
	}
}
