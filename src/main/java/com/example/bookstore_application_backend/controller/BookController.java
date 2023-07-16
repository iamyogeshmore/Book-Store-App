package com.example.bookstore_application_backend.controller;


import com.example.bookstore_application_backend.dto.ResponseDTO;
import com.example.bookstore_application_backend.dto.BookDTO;
import com.example.bookstore_application_backend.model.BookModel;
import com.example.bookstore_application_backend.service.IbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BooksPage")
@CrossOrigin("*")
public class BookController {

    @Autowired
    IbookService ibookService;

    //----------------------------- Add New Books (Only Admin)----------------------------------------------------------------------
    @PostMapping("/Add_Books/Admin")
    public ResponseEntity<ResponseDTO> addBooks(@RequestParam String token, @RequestBody BookDTO bookDTO) {
        ibookService.addBooks(token,bookDTO);
        ResponseDTO responseDTO = new ResponseDTO(bookDTO, "Book Added Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------- Update Books Data (Only Admin)------------------------------------------------------------------
    @PutMapping("/Update_Books_Data/Admin")
    public ResponseEntity<ResponseDTO> updateBook(@RequestParam String token, @RequestParam int id, @RequestBody BookDTO bookDTO) {
        BookModel update = ibookService.updateBooksData(token,id,bookDTO);
        ResponseDTO responseDTO = new ResponseDTO(update, "Book Update Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //--------------------------------- Delete Book Data (Only Admin)------------------------------------------------------------------
    @DeleteMapping("/Delete_Book/Admin")
    public ResponseEntity<ResponseDTO> deleteBook(@RequestParam String token, @RequestParam int id) {
        ibookService.deleteBookById(token, id);
        ResponseDTO responseDTO = new ResponseDTO("book deleted for id :" +id+" ", "Book delete Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------- Show All Books Data (AnyOne)--------------------------------------------------------------------
    @GetMapping("/Show All Books Data")
    public ResponseEntity<ResponseDTO> showAllBooksData(){
        List<BookModel> bookModelList = ibookService.showAllBooks();
        ResponseDTO responseDTO = new ResponseDTO(bookModelList, "All Books Data" );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------- Search Books by Book id (AnyOne)--------------------------------------------------------------------
    @GetMapping("/Find_Book_By_Id")
    public ResponseEntity<ResponseDTO> getBookById(@RequestParam int id) {
        BookModel bookModel = ibookService.getBookByID(id);
        ResponseDTO responseDTO = new ResponseDTO(bookModel, "successfully record founded for given id: " + id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------- Search Books by Book Name (AnyOne)--------------------------------------------------------------------
    @GetMapping("/Search_Books_By_Name")
    public ResponseEntity<ResponseDTO> searchBooksByName(@RequestParam String bookName) {
        List<BookModel> bookModelList = ibookService.searchBookByName(bookName);
        ResponseDTO responseDTO = new ResponseDTO(bookModelList, "successfully record founded for given book name: " + bookName);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------- Search Books by Author Name (AnyOne)--------------------------------------------------------------------
    @GetMapping("/Search_Books_By_Author_Name")
    public ResponseEntity<ResponseDTO> searchBooksByAuthorName(@RequestParam String authorName) {
        List<BookModel> bookModelList = ibookService.searchBookByAuthorName(authorName);
        ResponseDTO responseDTO = new ResponseDTO(bookModelList, "successfully record founded for given Author name: " + authorName);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Sort Book Data By Price High To Low (AnyOne)---------------------------------
    @GetMapping("/Sort_Books_By_Price_HighToLow")
    public ResponseEntity<ResponseDTO> sortBooksByPriceHighToLow() {
        List<BookModel> sortedList = ibookService.sortBookByPriceHighToLow();
        ResponseDTO responseDTO = new ResponseDTO(sortedList, "Sort Books By Price High To Low");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Sort Book Data By Price Low To High (AnyOne)------------------------------------------------
    @GetMapping("/Sort_Books_By_Price_LowToHigh")
    public ResponseEntity<ResponseDTO> sortBooksByPriceLowToHigh() {
        List<BookModel> sortedList = ibookService.sortBookByPriceLowToHigh();
        ResponseDTO responseDTO = new ResponseDTO(sortedList, "Sort Books By Price Low To High");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Sort Book Data By Newest Arrivals books (AnyOne)--------------------------------------------
    @GetMapping("/Sort_Books_By_Newest_Arrivals")
    public ResponseEntity<ResponseDTO> sortBooksByNewestArrivals() {
        List<BookModel> sortedList = ibookService.sortBooksByNewestArrivals();
        ResponseDTO responseDTO = new ResponseDTO(sortedList, "Sort Books By Newest Arrivals");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
