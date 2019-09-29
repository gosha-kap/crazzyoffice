package ru.crazzyoffice.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.crazzyoffice")
@EnableJpaRepositories(basePackages = "ru.crazzyoffice.repository")
@PropertySource("classpath:persistence-mysql.properties")
public class JPAConfig {

    @Autowired
    private Environment environment;

    private Logger logger = Logger.getLogger(getClass().getName());


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(securityDataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {
                "ru.crazzyoffice.entity"
        });

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());

        return entityManagerFactoryBean;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", environment.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache", environment.getProperty("hibernate.cache.use_query_cache"));
        return hibernateProperties;
    }


    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }


    @Bean
    public DataSource securityDataSource(){
        ComboPooledDataSource source = new ComboPooledDataSource();
        try {
            source.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw  new RuntimeException(e);
        }

        //logger.info(">>>>jdbc.url: "+environment.getProperty("jdbc.url"));
        //logger.info(">>>>jdbc.user: "+environment.getProperty("jdbc.user"));

        source.setJdbcUrl(environment.getProperty("jdbc.url"));
        source.setUser(environment.getProperty("jdbc.user"));
        source.setPassword(environment.getProperty("jdbc.password"));


        source.setInitialPoolSize(geIntProperty("connection.pool.initialPoolSize"));
        source.setMinPoolSize(geIntProperty("connection.pool.minPoolSize"));
        source.setMaxPoolSize(geIntProperty("connection.pool.maxPoolSize"));
        source.setMaxIdleTime(geIntProperty("connection.pool.maxIdleTime"));

        return  source;
    }

    private  int geIntProperty(String s){
        String property = environment.getProperty(s);
        return Integer.parseInt(property);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}




