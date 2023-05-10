package project.filters;

import io.jsonwebtoken.*;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import project.errors.UnauthorizedException;
import reactor.core.publisher.Mono;


public class AuthenticationFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String header = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedException("Token Iválido");
        }

        String token = header.substring(7);

        try {
            Claims claims = Jwts.parser().setSigningKey("r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B&E").parseClaimsJws(token).getBody();
            exchange.getRequest().mutate().header("documentNumber", claims.getSubject());
        } catch (ExpiredJwtException e){
            throw new UnauthorizedException("Token Expirado");
        }
        catch (JwtException | IllegalArgumentException e) {
            throw new UnauthorizedException("Token inválido");
        }

        return chain.filter(exchange);
    }
}




