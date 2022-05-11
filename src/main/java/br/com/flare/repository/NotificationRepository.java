package br.com.flare.repository;

import br.com.flare.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT * FROM notifications n JOIN categories c ON (c.id = n.category_id) WHERE c.name = :category",
            nativeQuery = true)
    List<Note> findByCategory(String category);
}
