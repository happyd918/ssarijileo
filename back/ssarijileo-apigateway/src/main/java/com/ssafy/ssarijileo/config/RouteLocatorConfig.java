//package com.ssafy.ssarijileo.config;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RouteLocatorConfig {
//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder, AuthorizationHeaderFilter) {
////        return builder.routes()
////                .route("no-filter", r -> r.path("/skip/**")
////                        .filters(f -> f.filter(new SkipPathFilter()))
////                        .uri("http://example.com"))
////                .route("with-filter", r -> r.path("/**")
////                        .filters(f -> f.filter(new MyFilter()))
////                        .uri("http://example.com"))
////                .build();
//        return builder.routes()
//                .route("no-filter", r -> r.path("/skip/**")
//                        .filters(f -> f.filter(new SkipPathFilter()))
//                        .uri("http://example.com"))
//                .route("with-filter", r -> r.path("/**")
//                        .filters(f -> f.filter(new MyFilter()))
//                        .uri("http://example.com"))
//                .build();
//    }
//}
