package projetofinal.bookstore_api.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetofinal.bookstore_api.model.Book;
import projetofinal.bookstore_api.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    // Método para atualizar um livro
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        // Procurar o livro no banco de dados
        Optional<Book> existingBookOpt = bookRepository.findById(id);

        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get();

            // Atualizar as informações do livro
            existingBook.setAuthor(bookDetails.getAuthor());
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setReleaseYear(bookDetails.getReleaseYear()); // Se for apenas o ano

            // Salvar a atualização no banco
            return bookRepository.save(existingBook);
        } else {
            throw new RuntimeException("Livro não encontrado com o id: " + id);
        }
    }
    // Método para excluir um livro
    @Transactional
    public void deleteBook(Long id) {
        // Verifica se o livro existe no banco
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado com o id: " + id);
        }

        // Deleta o livro
        bookRepository.deleteById(id);
    }
}
