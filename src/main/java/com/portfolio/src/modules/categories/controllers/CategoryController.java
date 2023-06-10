package com.portfolio.src.modules.categories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.src.modules.categories.domain.dtos.CategoryInDTO;
import com.portfolio.src.modules.categories.domain.dtos.CategoryOutDTO;
import com.portfolio.src.modules.categories.services.CategoryService;
import com.portfolio.src.shared.exceptions.NotFoundException;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping("/find-all")
	public ResponseEntity<Page<CategoryOutDTO>> findAll(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "id") String sort,
	        @RequestParam(defaultValue = "asc") String direction) {

	    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
	    Page<CategoryOutDTO> categoryPage = service.findAllCategory(pageable);

	    return new ResponseEntity<>(categoryPage, HttpStatus.OK);
	}


	@GetMapping("/find-one/{id}")
	public ResponseEntity<Object> findOne(@PathVariable("id") Integer id) {
		try {
			CategoryOutDTO category = service.findOneCategory(id);
			return new ResponseEntity<>(category, HttpStatus.OK);
		} catch (NotFoundException e) {
			String mensagem = e.getMessage();
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Object> created(@RequestBody CategoryInDTO dto) {
		try {
			CategoryOutDTO newCategory = service.create(dto);
			return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
		} catch (NotFoundException e) {
			String mensagem = e.getMessage();
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Object> updated(@PathVariable Integer id, @RequestBody CategoryInDTO dto) {
		try {
			CategoryOutDTO updatedCategory = service.update(id, dto);
			return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
		} catch (NotFoundException e) {
			String mensagem = e.getMessage();
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Object> deleted(@PathVariable Integer id) {
		try {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException e) {
			String mensagem = e.getMessage();
			return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
		}
    }

}
