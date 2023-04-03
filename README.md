# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## Notes
- @RestController annotation - tells spring that the class is a rest controller and load during the project initialization 
- @RequestMapping annotation - tells spring which url the controller will be called
- @MethodMapping annotation - tells which class method will be called for a HTTP request
  - @GetMapping - HTTP Get method
  - @PostMapping - HTTP Post method
  - @PutMapping - HTTP Put method
  - @DeleteMapping - HTTP Delete method
  - @PatchMapping - HTTP Patch method

### Enable different origins on Spring Boot
Create a `CrosConfiguration` class implementing the `WebMvcConfigurer`passing the addMapping (url), allowedOrigins and allowedMethods.@
```Java
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
```
#### Can use application.yml to configure the spring
```yml
  spring:
    datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/clinica
      username: root
      password: root

```