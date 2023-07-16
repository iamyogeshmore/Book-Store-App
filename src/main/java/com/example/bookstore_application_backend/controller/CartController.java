package com.example.bookstore_application_backend.controller;

import com.example.bookstore_application_backend.dto.ResponseDTO;
import com.example.bookstore_application_backend.model.CartBooksData;
import com.example.bookstore_application_backend.service.IcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/CartPage")
@CrossOrigin("*")
public class CartController {

    @Autowired
    IcartService icartService;


    //--------------------------------- Add New Cart Data (Only User)---------------------------------
    @PostMapping("/AddToCart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestParam String token, @RequestParam int bookId) {
        CartBooksData cart = icartService.addToCart(token, bookId);
        ResponseDTO responseDTO = new ResponseDTO(cart, "Books Added Into Cart Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Increase BookQty (Only User)---------------------------------
    @PutMapping("IncreaseBookQty")
    public ResponseEntity<ResponseDTO> increaseBookQty(@RequestParam String token, @RequestParam int bookId) {
        CartBooksData cart = icartService.increaseBookQty(token,bookId);
        ResponseDTO responseDTO = new ResponseDTO(cart, "You've changed '"+cart.getBooks().getBookName()+"' QUANTITY to '"+cart.getQuantity()+"'");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Decrease BookQty (Only User)---------------------------------
    @PutMapping("DecreaseBookQty")
    public ResponseEntity<ResponseDTO> decreaseBookQty(@RequestParam String token, @RequestParam int bookId) {
        CartBooksData cart = icartService.decreaseBookQty(token,bookId);
        ResponseDTO responseDTO = new ResponseDTO(cart, "You've changed '"+cart.getBooks().getBookName()+"' QUANTITY to '"+cart.getQuantity()+"'");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Delete Cart Data (Only User)---------------------------------
    @DeleteMapping("/Remove_Book_From_Cart")
    public ResponseEntity<ResponseDTO> removeBookFromCart(@RequestParam String token, @RequestParam int cartBookId) {
        icartService.removeBookFromCart(token,cartBookId);
        ResponseDTO responseDTO = new ResponseDTO("Removed Book for id: " + cartBookId, "Book Remove Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Show Cart Data(Books) ---------------------------------
    @GetMapping("/Show_Cart_Record")
    public ResponseEntity<ResponseDTO> showUserCartRecords(@RequestParam String token) {
        List<CartBooksData> cartRecord = icartService.showCartRecord(token);
        ResponseDTO responseDTO = new ResponseDTO(cartRecord, "Cart record retrieved successfully for User");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    //--------------------------------- Get_Total-Cart_Amount-Qty ---------------------------------

    @GetMapping("/Get_Total-Cart_Amount-Qty")
    public ResponseEntity<ResponseDTO> GetTotalCartAmountQty(@RequestParam String token) {
        double[] cartData = icartService.showTotalAmount_Qty(token);
        ResponseDTO responseDTO = new ResponseDTO(cartData, "Cart record retrieved successfully for User");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
