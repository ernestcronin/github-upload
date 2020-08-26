package recipes.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RecipesApp {

	public static void main(final String... args) {
        SpringApplication.run(new Class[] { RecipesApp.class }, args);
    }
}
