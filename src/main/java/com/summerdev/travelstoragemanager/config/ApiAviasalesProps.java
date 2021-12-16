package com.summerdev.travelstoragemanager.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "api.aviasales")
public class ApiAviasalesProps {

    private String token;

    private ApiAviasalesPropUrls urls;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApiAviasalesPropUrls getUrls() {
        return urls;
    }

    public void setUrls(ApiAviasalesPropUrls urls) {
        this.urls = urls;
    }

    public static class ApiAviasalesPropUrls {

        private String cheap;

        private String supportedRoutes;

        private String priceMap;

        public String getCheap() {
            return cheap;
        }

        public void setCheap(String cheap) {
            this.cheap = cheap;
        }

        public String getSupportedRoutes() {
            return supportedRoutes;
        }

        public void setSupportedRoutes(String supportedRoutes) {
            this.supportedRoutes = supportedRoutes;
        }

        public String getPriceMap() {
            return priceMap;
        }

        public void setPriceMap(String priceMap) {
            this.priceMap = priceMap;
        }
    }
}

