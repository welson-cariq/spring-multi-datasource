package com.example.multi.datasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;

public class DatabaseConfig {

    @Autowired
    private Environment env;

    protected LocalContainerEntityManagerFactoryBean configEntityManager(DataSource dataSource, String packageToScan) {
        var em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(packageToScan);

        var vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        var properties = new HashMap<String, String>();
        properties.put("hibernate.hbm2ddl.auto", getDdlAuto());
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    private String getDdlAuto() {
        var ddlAuto = env.getProperty("DDL_AUTO");
        if (!StringUtils.hasText(ddlAuto)) {
            ddlAuto = "none";
        }
        return ddlAuto;
    }
}
