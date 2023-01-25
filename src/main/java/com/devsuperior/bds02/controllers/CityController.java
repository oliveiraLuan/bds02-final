package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(Pageable pageable){
		List<CityDTO> cities = cityService.findAll();
		return ResponseEntity.ok().body(cities);
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dtoRequest){
		CityDTO dtoResponse = cityService.insert(dtoRequest);
		URI uri = UriComponentsBuilder.fromPath("/cities").buildAndExpand().toUri();
 		return ResponseEntity.created(uri).body(dtoResponse);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cityService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}

