package br.com.flare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flare.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByIdAsc();
    
}
