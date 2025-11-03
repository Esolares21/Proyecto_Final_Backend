package umg.edu.sistema_eventos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaEventosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaEventosApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // 1. Módulo para Java Time (maneja LocalDateTime)
        mapper.registerModule(new JavaTimeModule());

        // 2. Módulo de Hibernate (maneja Lazy Loading y proxies)
        mapper.registerModule(new Hibernate5JakartaModule());

        return mapper;
    }

}