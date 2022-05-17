package br.com.flare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flare.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByIdAsc();

    @Query(value = "SELECT c FROM Category c WHERE c.name IN :names")
    List<Category> findAllByName(@Param("names") List<String> names);
}
