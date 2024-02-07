//package com.rd.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.web.http.CookieSerializer;
//import org.springframework.session.web.http.DefaultCookieSerializer;
//
////@Configuration
////@EnableRedisHttpSession
//public class SessionConfig {
//
////	
////	@Bean
////    public CookieSerializer cookieSerializer() {
////        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
////        serializer.setCookieName("CUSTOM_SESSION_ID"); // Set your custom cookie name
////        serializer.setCookiePath("/");
////        serializer.setCookieMaxAge(3600); // Set the cookie expiration time
////        serializer.setUseHttpOnlyCookie(true);
////        return serializer;
////    }
//}
