package projetofinal.bookstore_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetofinal.bookstore_api.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}