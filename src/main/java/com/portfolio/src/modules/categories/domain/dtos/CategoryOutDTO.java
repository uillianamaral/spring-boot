package com.portfolio.src.modules.categories.domain.dtos;

import java.util.Objects;

public class CategoryOutDTO {

	private Integer id;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryOutDTO other = (CategoryOutDTO) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}
	
	
}
