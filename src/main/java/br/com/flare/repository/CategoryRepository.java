package br.com.flare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flare.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
