package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.ImageRepository;

public class ImageService {

	private ImageRepository imageRepository;

	public Image findById(Long id) {
		return this.imageRepository.findById(id).orElse(null);
	}

}
