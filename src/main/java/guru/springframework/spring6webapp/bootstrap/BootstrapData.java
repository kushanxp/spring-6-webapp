package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    @Value("${myhostname}")
    String myName;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher wiley = new Publisher();
        wiley.setPublisherName("Wiley");
        wiley.setAddress("54th Street");
        wiley.setCity("NewYork");
        wiley.setZip("12345");
        wiley.setState("NewYork");

        Publisher savedWiley = publisherRepository.save(wiley);

        Author kushan = new Author();
        kushan.setFirstName("Shawn");
        kushan.setLastName("Jayz");

        Author saveKushan = authorRepository.save(kushan);


        Book dummy = new Book();
        dummy.setTitle("Java for dummies");
        dummy.setIsbn("123456");
        dummy.setPublisher(savedWiley);
        dummy.getAuthors().add(saveKushan);

        Book saveDummy = bookRepository.save(dummy);

        kushan.getBooks().add(saveDummy);
        saveKushan = authorRepository.save(kushan);


        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Author rodSave = authorRepository.save(rod);

        Book rodBook = new Book();
        rodBook.setTitle("J2EE without EJB");
        rodBook.setIsbn("098765");
        rodBook.setPublisher(savedWiley);

        Book saveRodBook = bookRepository.save(rodBook);

        saveKushan.getBooks().add(saveDummy);
        rodSave.getBooks().add(saveRodBook);

        saveDummy.getAuthors().add(saveKushan);
        saveRodBook.getAuthors().add(rodSave);

        System.out.println("In Bootstrap.....");
        System.out.println("BookRepo count : " + bookRepository.count());
        System.out.println("AuthorRepo count : " + authorRepository.count());
        System.out.println("myname  from ENV : " + myName);


        System.out.println("Publisher count : " +publisherRepository.count());


    }
}
