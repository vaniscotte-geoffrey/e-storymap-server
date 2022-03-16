package fr.jaune.estorymap.repository;

import fr.jaune.estorymap.model.document.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
}
