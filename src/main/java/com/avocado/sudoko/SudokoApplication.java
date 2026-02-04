package com.avocado.sudoko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }) // exclude the db connection (tells Spring don't try to configure a connection automatically)
public class SudokoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SudokoApplication.class, args);
    }

}
