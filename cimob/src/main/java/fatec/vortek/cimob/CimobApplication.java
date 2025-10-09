package fatec.vortek.cimob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CimobApplication {

    public static void main(String[] args) {
        SpringApplication.run(CimobApplication.class, args);
    }
}
