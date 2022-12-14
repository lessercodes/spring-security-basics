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

Basic Auth flow:

![](/docs/images/basic-auth.png)

1. Try to access secured endpoint
2. Without providing valid credentials `FilterSecurityInterceptor` throws an `AccessDeniedException` indicating that the request was denied.
3. The `ExceptionTranslationFilter` handles the exception and initiates `Start Authentication`, which sends a `WWW-Authenticate` header to the client.

To access the secured endpoint we should use the credentials provided by Spring Security. By default, Spring Security creates a user 
named `user` with a randomly generated password in the form of a UUID string. The password can be obtained by taking a look
at the logs generated by the application.

To customize the default user we can use the following two properties:
```properties
spring.security.user.name
spring.security.user.password
```

If we want to use multiple users we need to provide a `UserDetailsManager` bean. `UserDetailsManager` is an interface and
it's simplest implementation is the `InMemoryUserDetailsManager`.

```java
@Bean
public UserDetailsManager userDetailsManager() {
    final UserDetails user = User.builder()
            .username("user_1")
            .password("password_1")
            .roles("ROLE")
            .build();

    return new InMemoryUserDetailsManager(user);
}
```

This configuration creates a user in place of the default one. In this case the username will be `user_1` and the password
will be `password_1`.

If we use the code above we will run into some issues. When we try to log in with the configures credentials, Spring Security 
tries to encode the provided password and compare it to the configured one. But because we did not specify a `PasswordEncoder`
this will fail with an Exception.

The solution for this problem is simple, we need to provide a `PasswordEncoder` bean like the following.

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

There are multiple implementations of the `PasswordEncoder` interface, `BCryptPasswordEncoder` is just one of them.
With the `DelegatingPasswordEncoder` implementation we can even use multiple encoders besides each other.