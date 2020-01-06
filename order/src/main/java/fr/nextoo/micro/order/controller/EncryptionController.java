package fr.nextoo.micro.order.controller;

import fr.nextoo.micro.order.service.IEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EncryptionController {

    private final IEncryptionService encryptionService;

    @Autowired
    public EncryptionController(IEncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @PostMapping("/order/file")
    private ResponseEntity<Resource> encrypt(@RequestParam("file") MultipartFile file, @RequestParam("key") String key) {
        return ResponseEntity.ok(encryptionService.encrypt(file, key));
    }

    @GetMapping("/order/file/{filename}")
    private ResponseEntity<Resource> decrypt(@PathVariable String filename, @RequestParam("key") String key) {
        return ResponseEntity.of(encryptionService.decrypt(filename, key));
    }
}
