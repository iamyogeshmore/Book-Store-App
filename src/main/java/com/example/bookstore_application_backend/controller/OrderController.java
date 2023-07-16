package com.example.bookstore_application_backend.controller;

import com.example.bookstore_application_backend.dto.ResponseDTO;
import com.example.bookstore_application_backend.dto.OrderDTO;
import com.example.bookstore_application_backend.model.OrderModel;
import com.example.bookstore_application_backend.service.IorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/OrderPage")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    IorderService iorderService;


    //--------------------------------- place Order (Only User)-----------------------------------------------------------------------------------------
    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestParam String token, @Valid @RequestBody OrderDTO orderDTO) {
        OrderModel orderModel = iorderService.placeOrder(token, orderDTO);
        ResponseDTO responseDTO = new ResponseDTO(orderModel, "Order Placed Successfully!!!" +
                                                             "\nThank you for your order! " +
                                                             "\nYour order number is #"+orderModel.getOrderId()+". We have emailed your order " +
                                                             "\nconfirmation, and will send you an update when your order has shipped.");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Get Order Data (Only User) ---------------------------------------------------------------------------------
    @GetMapping("/Show_All_Orders/User")
    public ResponseEntity<ResponseDTO> showUserOrders(@RequestParam String token) {
        List<OrderModel> orders = iorderService.showUserAllOrders(token);
        ResponseDTO responseDTO = new ResponseDTO(orders, "Record found successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Get Order Data by Order ID (Only User) --------------------------------------------
    @GetMapping("/get_Order_Details_by_OrderId/User")
    public ResponseEntity<ResponseDTO> getOrderDetailsByOrderIdForUser(@RequestParam String token, @RequestParam int orderId) {
        OrderModel orderDetails = iorderService.getOrderDetailsByOrderIdForUser(token, orderId);
        ResponseDTO responseDTO = new ResponseDTO(orderDetails, "Order Details Found for Order ID:" + orderId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Change Order Mobile No (Only user) --------------------------------------------------------------------------
    @PutMapping("/Change_Order_PhoneNo")
    public ResponseEntity<ResponseDTO> changeOrderAddressOrPhoneNo(@RequestParam String token, @RequestParam int orderId, @RequestParam String phoneNo) {
        OrderModel updateOrder = iorderService.changeMobileNo(token, orderId, phoneNo);
        ResponseDTO responseDTO = new ResponseDTO(updateOrder, "Order Phone Number Change Successful");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Cancel Order (Only user) -----------------------------------------------------------------------------------
    @PostMapping("/Cancel_Order")
    public ResponseEntity<ResponseDTO> cancelOrder(@RequestParam String token, @RequestParam int orderId) {
        iorderService.cancelOrder(token, orderId);
        ResponseDTO responseDTO = new ResponseDTO("Cancel order for Order id: " + orderId, "You've successfully cancelled your order");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Get All Orders Data (Only Admin) ---------------------------------------------------------------------------
    @GetMapping("/Show_All_Orders/Admin")
    public ResponseEntity<ResponseDTO> GetAllOrdersDataAsAdmin(@RequestParam String token) {
        List<OrderModel> allOrders = iorderService.adminGetAllOrderDetails(token);
        ResponseDTO responseDTO = new ResponseDTO(allOrders, "Record found successfully");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //--------------------------------- Get Order Data by Order ID (only Admin) --------------------------------------------
    @GetMapping("/get_Order_Details_by_OrderId/Admin")
    public ResponseEntity<ResponseDTO> getOrderDetailsByOrderIdForAdmin(@RequestParam String token, @RequestParam int orderId) {
        OrderModel orderDetails = iorderService.getOrderDetailsByOrderIdForAdmin(token, orderId);
        ResponseDTO responseDTO = new ResponseDTO(orderDetails, "Order Details Found for Order ID:" + orderId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}


