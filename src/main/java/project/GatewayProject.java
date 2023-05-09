package project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@ConfigurationPropertiesScan(basePackageClasses = GatewayProject.class)
public class GatewayProject {
    public static void main(String[] args) {
        SpringApplication.run(GatewayProject.class, args);
    }
}