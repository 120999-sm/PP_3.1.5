package com.example.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBDropCreateListener implements ApplicationListener<ContextClosedEvent> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBDropCreateListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        jdbcTemplate.execute("DROP DATABASE my_db");
        jdbcTemplate.execute("CREATE DATABASE my_db");
    }
}
