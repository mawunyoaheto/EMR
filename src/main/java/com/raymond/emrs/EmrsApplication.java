package com.raymond.emrs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "EMR  API", version = "1.0", description = "Electronic Medical Records System"))
public class EmrsApplication {

    public static void main(String[] args) {
        System.out.println("App start...");
        SpringApplication.run(EmrsApplication.class, args);
        System.out.println("App end...");
    }

}
