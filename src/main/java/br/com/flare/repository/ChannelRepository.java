package br.com.flare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.flare.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByNameAsc();

    @Query(value = "SELECT c FROM Category c WHERE c.name IN :names")
    List<Category> findAllByName(@Param("names") List<String> names);

    @Query(value = "SELECT * FROM CATEGORIES c INNER JOIN USERS_INSCRIBED_CATEGORIES uic ON (c.id = uic.inscribed_categories_id) INNER JOIN USERS u on (uic.user_id = u.id) WHERE u.name = :name", nativeQuery = true)
    List<Category> findAllByUserRegistred(String name);
}
