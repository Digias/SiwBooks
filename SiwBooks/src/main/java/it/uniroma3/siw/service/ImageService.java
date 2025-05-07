package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.ImageRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImageService {

	@Autowired private ImageRepository imageRepository;

	public Image findById(Long id) {
		return this.imageRepository.findById(id).orElse(null);
	}

}
