package fatec.vortek.cimob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CimobApplication {

    public static void main(String[] args) {
        SpringApplication.run(CimobApplication.class, args);
    }
}
