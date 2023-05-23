package sit.int204.previousexam.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Setter
@Getter
@ConfigurationProperties(prefix = "file")   // Register at Application
public class FileStorageProperty {
    private String uploadDir;
}
