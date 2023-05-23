package sit.int204.previousexam;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import sit.int204.previousexam.properties.FileStorageProperty;
import sit.int204.previousexam.utils.ListMapper;

@SpringBootApplication

// Register *** & try to run
@EnableConfigurationProperties({
		FileStorageProperty.class
})
public class PreviousExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreviousExamApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	public ListMapper listMapper() {
		return ListMapper.getInstance();
	}
}
