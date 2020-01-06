package fr.nextoo.micro.order.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IEncryptionService {

    Resource encrypt(MultipartFile file, String key);

    Optional<Resource> decrypt(String filename, String key);

}
