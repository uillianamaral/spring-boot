package com.portfolio.src.modules.categories.domain.dtos;

import java.util.Objects;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryInDTO {

	@NotNull(message = "Por favor preenche o campo descrição")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryInDTO other = (CategoryInDTO) obj;
		return Objects.equals(description, other.description);
	}
	
	
}
