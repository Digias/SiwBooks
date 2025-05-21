package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.service.ImageService;

@Controller
public class ImageController {

	@Autowired private ImageService imageService;

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
	    Image image = imageService.findById(id);
	    return ResponseEntity.ok()
	        .contentType(MediaType.parseMediaType(image.getContentType()))
	        .body(image.getData());
	}

}
