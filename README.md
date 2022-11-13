# Spring Security Basics

This is a sandbox project that was created for the sole purpose of learning Spring Security and take notes in the process.

### Spring Security dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Because of Spring Auto Configuration, adding the dependency automatically secures the endpoints and provides a 
login (`/login`) and logout (`/logout`) page.

### Basic Auth

Basic Auth can be configured by customizing the `SecurityFilterChain` bean.

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .authorizeRequests(conf -> {
            conf.anyRequest().authenticated();
        })
        .httpBasic()
        .and()
        .build();
}
```

The configuration above secures all endpoint with Basic Auth. 

Basic Auth flow:

![](/docs/images/basic-auth.png)

1. Request secured endpoint
2. Without providing valid credentials `FilterSecurityInterceptor` throws an `AccessDeniedException` indicating that the request was denied.
3. The `ExceptionTranslationFilter` handles the exception by initiating `Start Authentication`, which sends a `WWW-Authenticate` header to the client.