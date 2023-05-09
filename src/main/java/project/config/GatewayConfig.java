package project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import project.filters.AuthenticationFilter;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r
                        .path("/user/auth")
                        .uri("http://localhost:8181/user/auth"))
                .route("user-get-one", r -> r
                        .path("/user/get-one")
                        .filters(f -> f.filter(new AuthenticationFilter()))
                        .uri("http://localhost:8181/user/get-one"))
                .route("user-create", r -> r
                        .path("/user/create")
                        .filters(f -> f.filter(new AuthenticationFilter()))
                        .uri("http://localhost:8181/user/create"))
                .build();
    }
}