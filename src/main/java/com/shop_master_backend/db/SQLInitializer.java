package com.shop_master_backend.db;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class SQLInitializer {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Transactional
    public void init() throws Exception {
        loadImportSql();
    }

    private void loadImportSql() throws Exception {
        try {
            String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/insert.sql")));
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
