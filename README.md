# Voll Med API Rest

## Appointmets
- [x] O horário de funcionamento da clínica é de segunda a sábado, das 07:00 às 19:00;
- [] As consultas tem duração fixa de 1 hora;
- [x] As consultas devem ser agendadas com antecedência mínima de 30 minutos;
- [x] Não permitir o agendamento de consultas com pacientes inativos no sistema;
- [x] Não permitir o agendamento de consultas com médicos inativos no sistema;
- [x] Não permitir o agendamento de mais de uma consulta no mesmo dia para um mesmo paciente;
- [x] Não permitir o agendamento de uma consulta com um médico que já possui outra consulta agendada na mesma data/hora;
- [x] A escolha do médico é opcional, sendo que nesse caso o sistema deve escolher aleatoriamente algum médico disponível na data/hora preenchida.
## Notes
- @RestController annotation - tells spring that the class is a rest controller and load during the project initialization 
- @RequestMapping annotation - tells spring which url the controller will be called
- @MethodMapping annotation - tells which class method will be called for a HTTP request
  - @GetMapping - HTTP Get method
  - @PostMapping - HTTP Post method
  - @PutMapping - HTTP Put method
  - @DeleteMapping - HTTP Delete method
  - @PatchMapping - HTTP Patch method
- @Transactional annotation - tells spring that the method can write on the DB 


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
#### Change the default parameters names
spring.data.web.pageable.page-parameter=pagina<br>
spring.data.web.pageable.size-parameter=tamanho<br>
spring.data.web.sort.sort-parameter=ordem