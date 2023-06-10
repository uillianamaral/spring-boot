package com.portfolio.src.modules.categories.repositorys;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.src.shared.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	@Query("SELECT C FROM Category C WHERE C.id = :id")
	public Category findCategoryId(@Param("id") Integer id);
}
