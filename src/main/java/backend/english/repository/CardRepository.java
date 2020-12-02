package backend.english.repository;

import backend.english.domain.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    @Query("SELECT c FROM cards c where (CAST (:category AS text) IS NULL OR c.category.id = :category)")
    Page<Card> findCardByParams(@Param("category") UUID category, Pageable pageable);
}
