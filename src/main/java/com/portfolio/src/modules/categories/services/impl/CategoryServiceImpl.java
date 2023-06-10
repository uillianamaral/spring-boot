package com.portfolio.src.modules.categories.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portfolio.src.modules.categories.domain.dtos.CategoryInDTO;
import com.portfolio.src.modules.categories.domain.dtos.CategoryOutDTO;
import com.portfolio.src.modules.categories.repositorys.CategoryRepository;
import com.portfolio.src.modules.categories.services.CategoryService;
import com.portfolio.src.shared.entities.Category;
import com.portfolio.src.shared.exceptions.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repo;

	@Override
	public Page<CategoryOutDTO> findAllCategory(Pageable pageable) {

		Page<Category> categoryPage = repo.findAll(pageable);

		List<CategoryOutDTO> newListCategories = new ArrayList<>();

		categoryPage.getContent().forEach(category -> {
			CategoryOutDTO newCategorie = new CategoryOutDTO();
			newCategorie.setId(category.getId());
			newCategorie.setDescription(category.getDescription());
			newListCategories.add(newCategorie);
		});

		return new PageImpl<>(newListCategories, pageable, categoryPage.getTotalElements());
	}

	@Override
	public CategoryOutDTO findOneCategory(Integer id) {

		Category category = repo.findCategoryId(id);

		if (category != null) {

			CategoryOutDTO newCategory = new CategoryOutDTO();

			newCategory.setId(category.getId());
			newCategory.setDescription(category.getDescription());

			return newCategory;

		} else {
			throw new NotFoundException("A categoria não foi encontrada.");
		}
	}

	@Override
	public CategoryOutDTO create(CategoryInDTO dto) {
		
		if(dto.getDescription().isEmpty())
			throw new NotFoundException("Por favor, preenche os dados corretamente!");

		Category c = new Category();
		c.setDescription(dto.getDescription());

		Category category = repo.saveAndFlush(c);

		if (category != null) {
			CategoryOutDTO newCategory = new CategoryOutDTO();
			newCategory.setId(category.getId());
			newCategory.setDescription(category.getDescription());
			return newCategory;
		} else {
			throw new NotFoundException("Error ao criar a Categoria");
		}
 
	}

	@Override
	public CategoryOutDTO update(Integer id, CategoryInDTO dto) {
		
		if(dto.getDescription().isEmpty())
			throw new NotFoundException("Por favor, preenche os dados corretamente!");

		Optional<Category> category = repo.findById(id);

		if (category.isPresent()) {

			Category c = new Category();
			c.setId(category.get().getId());
			c.setDescription(dto.getDescription());

			repo.save(c);

			CategoryOutDTO updateCategory = new CategoryOutDTO();
			updateCategory.setId(c.getId());
			updateCategory.setDescription(c.getDescription());
			return updateCategory;
		} else {
			throw new NotFoundException("Error ao atualizar a Categoria");
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		
		Optional<Category> category = repo.findById(id);
		
		if(category.isPresent()) {			
			repo.deleteById(id);
		} else {
			throw new NotFoundException("Error ao deletar a Categoria");
		}
	}

}
