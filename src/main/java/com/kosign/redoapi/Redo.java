package com.kosign.redoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.kosign.redoapi.properties")
public class Redo {

    public static void main(String[] args) {
        SpringApplication.run(Redo.class, args);
    }

}
