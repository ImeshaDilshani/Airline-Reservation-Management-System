package com.jkshian.arms.demo;

import com.jkshian.arms.dto.AuthenticationResponse;
import com.jkshian.arms.dto.BookingDto;
import com.jkshian.arms.dto.Planedto;
import com.jkshian.arms.dto.RegisterRequest;
import com.jkshian.arms.entity.Booking;
import com.jkshian.arms.entity.User;
import com.jkshian.arms.service.AuthenticationService;
import com.jkshian.arms.service.BookingService;
import com.jkshian.arms.service.PlaneService;
import com.jkshian.arms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    private final PlaneService planeService;
    private final UserService userService;
    private final AuthenticationService service;
    private final BookingService bookingService;


    public DemoController(PlaneService planeService, UserService userService, AuthenticationService service, BookingService bookingService) {
        this.planeService = planeService;
        this.userService = userService;
        this.service = service;
        this.bookingService = bookingService;
    }

    @GetMapping("/index")
    public String index(){
        return "User/index";
    }


    // Admin only can access this method
    @PostMapping("/admin/addplane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addNewPlane(@RequestBody Planedto planedto) {
           return  planeService.createNewPlane(planedto);
    }

    // Admin only can access this method
    @GetMapping("/admin/getPlaneById")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Planedto getPlaneById(@RequestParam("id") int id){
        return  planeService.getPlaneById(id);
    }

    // Admin only can access this method
    @GetMapping("/admin/getAllPlane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Planedto> getAllPlane(){
        return  planeService.getAllPlane();
    }

    // Admin only can access this method
    @PutMapping("/admin/updateplane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updatePlane(@RequestBody Planedto planedto){
        return  planeService.updatePlane(planedto);
    }

    // Admin only can access this method
    @DeleteMapping("/admin/deletePlaneById")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public  ResponseEntity<String> deletePlaneById(@RequestParam("id") int id){
        return  planeService.deletePlaneById(id);
    }

    // Admin only can access this method
    @GetMapping("/admin/getAllUsers")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<User> getAllUsers(){
        return  userService.getAllUsers();
    }

    // Admin only can access this method
    @PostMapping("/admin/register")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ){
        return  ResponseEntity.ok(service.registerAdmin(request));
    }

    // Admin only can access this method
    @GetMapping("/admin/getAllbooking")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Booking> getAllBooking(){
        return  bookingService.getAllBooking();
    }

    // User only can access this method
    @GetMapping("/checkPrice")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<Double> checkPrice(@RequestBody BookingDto bookingDto){
        return bookingService.checkPrice(bookingDto);
    }

    // User only can access this method
    @PostMapping("/checkPrice/addBooking")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<String> addBooking(@RequestBody BookingDto bookingdto){
        return  bookingService.addBooking(bookingdto);
    }

}
