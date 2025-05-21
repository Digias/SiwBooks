package it.uniroma3.siw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Image> image = imageService.getImage(id);
        return image.map(img -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Usa IMAGE_PNG se necessario
                        .body(img.getData()))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

