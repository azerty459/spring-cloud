package fr.nextoo.micro.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FileStorageProperties {
    @Value("${file.upload-dir}")
    private String uploadDir;
}
