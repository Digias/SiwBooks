package it.uniroma3.siw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /*
			UPLOAD IMAGE FORM DB TO WEB
     */
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Image> image = imageService.getImage(id);
        return image.map(img -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(img.getData()))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

