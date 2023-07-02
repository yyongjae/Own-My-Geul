package omg.omgspringbootapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class OmgSpringbootAppApplication

fun main(args: Array<String>) {
	runApplication<OmgSpringbootAppApplication>(*args)
}
