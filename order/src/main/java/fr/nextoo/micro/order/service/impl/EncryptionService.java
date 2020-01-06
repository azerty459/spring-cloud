package fr.nextoo.micro.order.service.impl;

import fr.nextoo.micro.order.FileStorageProperties;
import fr.nextoo.micro.order.exception.FileNotFoundException;
import fr.nextoo.micro.order.exception.FileStorageException;
import fr.nextoo.micro.order.service.IEncryptionService;
import fr.nextoo.micro.order.transformer.AES256FileEncryptionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class EncryptionService implements IEncryptionService {

    private final AES256FileEncryptionTransformer transformer;
    private final Path fileStorageLocation;

    @Autowired
    public EncryptionService(AES256FileEncryptionTransformer transformer,
                             FileStorageProperties fileStorageProperties) {
        this.transformer = transformer;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Resource encrypt(MultipartFile file, String key) {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        byte[] data = transformer.encrypt(file, key);
        write(filename, new ByteArrayInputStream(data));
        return new ByteArrayResource(data);
    }

    @Override
    public Optional<Resource> decrypt(String filename, String key) {
        try {
            return Optional.of(new ByteArrayResource(transformer.decrypt(read(filename), key)));
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    private byte[] read(String filename) {
        if (filename.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
        }

        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            return Files.readAllBytes(filePath);
        } catch (Exception e) {
            throw new FileNotFoundException("File not found " + filename, e);
        }
    }

    private void write(String filename, InputStream stream) {
        if (filename.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
        }
        Path targetLocation = this.fileStorageLocation.resolve(filename);
        try {
            Files.copy(stream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + filename + ". Please try again!", e);
        }
    }
}
