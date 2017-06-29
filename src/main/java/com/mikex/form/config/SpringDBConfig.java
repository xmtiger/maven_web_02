/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mikex.form.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 *
 * @author xmtig
 */
@Configuration
public class SpringDBConfig {
    
    @Autowired
    DataSource dataSource;
    
    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Bean
    public DataSource getDataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setName("testdb").setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/sql/create-db.sql").addScript("db/sql/insert-data.sql").build();
        
        return db;
    }
}
