FROM gcr.io/distroless/java17-debian11:nonroot

ENV TZ=America/Recife

WORKDIR /opt/api-gateway

EXPOSE 8180

COPY --chown=nonroot:nonroot ./target/gateway-1.0.0.jar /opt/api-gateway/gatewayservice.jar

CMD ["/opt/api-gateway/gatewayservice.jar", "-Xms128m", "-Xmx256m"]
