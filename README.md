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