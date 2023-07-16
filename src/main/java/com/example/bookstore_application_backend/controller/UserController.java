package com.example.bookstore_application_backend.controller;

import com.example.bookstore_application_backend.dto.ResponseDTO;
import com.example.bookstore_application_backend.dto.LoginDTO;
import com.example.bookstore_application_backend.dto.RegisterDTO;
import com.example.bookstore_application_backend.dto.UpdateDTO;
import com.example.bookstore_application_backend.model.UserModel;
import com.example.bookstore_application_backend.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserPage")
@CrossOrigin("*")
public class UserController {
    @Autowired
    IuserService iuserService;

    //--------------------------------- Add New User Data ---------------------------------

    @PostMapping("/Register_New_User")
    public ResponseEntity<ResponseDTO> registerNewUser(@RequestBody RegisterDTO registerDTO) {
        iuserService.registerNewUser(registerDTO);
        ResponseDTO responseDTO = new ResponseDTO(registerDTO, "User Registered Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- User Login (Both User or Admin)---------------------------------

    @PostMapping("/Login")
    public ResponseEntity<ResponseDTO> loginPage(@RequestBody LoginDTO loginDTO) {

        String [] obj = iuserService.login(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO(obj, "Login Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- User Logout (Both User or Admin)---------------------------------
    @PostMapping("/Logout")
    public ResponseEntity<ResponseDTO> logOutUser(@RequestParam String token) {
        iuserService.logout(token);
        ResponseDTO responseDTO = new ResponseDTO("User Logout", "SuccessFully Logout");
        return  new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Forgot Password (Both User or Admin)---------------------------------
    @PutMapping("/Forgot_Password")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestParam String token, @RequestParam String password) {
        iuserService.forgotPassword(token, password);
        ResponseDTO responseDTO = new ResponseDTO("Please Login again with new password", "Password changed Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Delete User Data (Only User)---------------------------------
    @DeleteMapping("/DeleteUser/user")
    public ResponseEntity<ResponseDTO> delete(@RequestParam String token) {
        iuserService.delete(token);
        ResponseDTO responseDTO = new ResponseDTO("Deleted for User: ", "Deleted Successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Delete User (Only Admin) ---------------------------------
    @DeleteMapping("/DeleteUser/Admin")
    public ResponseEntity<ResponseDTO> deleteUserAsAdmin(@RequestParam String token, @RequestParam int id) {
        iuserService.deleteUserAsAdmin(token, id);
        ResponseDTO responseDTO = new ResponseDTO("Deleted for User: " +id, "Deleted Successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Get User Data (Only User) ---------------------------------
    @GetMapping("/Get_Data/user")
    public ResponseEntity<ResponseDTO> getUserData(@RequestParam String token) {
        UserModel getUserData = iuserService.getUserData(token);
        ResponseDTO responseDTO = new ResponseDTO(getUserData, "User Data");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------- Update UserData (Only User)--------------------------------

    @PutMapping("/UpdateData/user")
    public ResponseEntity<ResponseDTO> updateData(@RequestBody UpdateDTO updateDTO, @RequestParam String token)  {
        UserModel update = iuserService.update(updateDTO, token);
        ResponseDTO responseDTO = new ResponseDTO(update, "User Updated Successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //----------------------------- Show_All_UserData (Only Admin)--------------------------------


    @GetMapping("/Show_All_User/Admin")
    public ResponseEntity<ResponseDTO> getAllUser(@RequestParam String token){
        List<UserModel> userModelList = iuserService.showAllUsers(token);
        ResponseDTO responseDTO = new ResponseDTO(userModelList, "All users Data" );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
