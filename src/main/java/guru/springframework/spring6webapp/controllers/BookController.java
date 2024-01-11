package guru.springframework.spring6webapp.controllers;

import guru.springframework.spring6webapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/books")
@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/list")
    public String getBooks(Model model) {

        model.addAttribute("books", bookService.findAll());
        return "books";
    }

}
