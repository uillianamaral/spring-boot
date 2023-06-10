package com.portfolio.src.modules.categories.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.portfolio.src.modules.categories.domain.dtos.CategoryInDTO;
import com.portfolio.src.modules.categories.domain.dtos.CategoryOutDTO;

public interface CategoryService {

	public Page<CategoryOutDTO> findAllCategory(Pageable pageable);

	public CategoryOutDTO findOneCategory(Integer id);

	public CategoryOutDTO create(CategoryInDTO dto);
	
	public CategoryOutDTO update(Integer id, CategoryInDTO dto);
	
	public void delete(Integer id);

}
