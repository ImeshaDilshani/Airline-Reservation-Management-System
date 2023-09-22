package com.jkshian.arms.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jkshian.arms.dto.*;
import com.jkshian.arms.entity.Booking;
import com.jkshian.arms.entity.User;
import com.jkshian.arms.service.AuthenticationService;
import com.jkshian.arms.service.BookingService;
import com.jkshian.arms.service.PlaneService;
import com.jkshian.arms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/indexForRegistation")
     public String indexForRegistationUsers() {
       return "/User/index";
        }

    @PostMapping("/index")
    public String index(@RequestBody AuthenticationRequest request) {
        if (userService.checkUserRoleIsAdmin(request)) {
            return "/Admin/dashboard";
        } else {
            return "/User/index";
        }
    }


    // Admin only can access this method
    @PostMapping("/admin/addplane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addNewPlane(@RequestBody Planedto planedto) {
        return planeService.createNewPlane(planedto);
    }

    // Admin only can access this method
     @GetMapping("/admin/getPlaneById")
     @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
     public ResponseEntity<String> getPlaneById(@RequestParam("id") int id,Model model) {
         Planedto findPlane= planeService.getPlaneById(id);
         System.out.println("Start :"+findPlane.getStart()+ "     end :"+ findPlane.getEnd());
         model.addAttribute("Plane",findPlane);
         return null;
     }

//    @GetMapping("/admin/getPlaneById")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    public String getPlaneById(@RequestParam("id") int id,Model model) {
//        Planedto findPlane= planeService.getPlaneById(id);
//        model.addAttribute("Plane",findPlane);
//        return "/Admin/flights";
//    }



    // Admin only can access this method
    @GetMapping("/admin/getAllPlane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getAllPlane(Model model) {
        List<Planedto> planes= planeService.getAllPlane();
        model.addAttribute("Planes",planes);
        for (Planedto plane: planes) {
            System.out.println(plane.getStart() + "   " + plane.getEnd());
        }
        return null;
    }

//    @GetMapping("/admin/getAllPlane")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    public String getAllPlane(Model model) {
//      List<Planedto> planes= planeService.getAllPlane();
//        model.addAttribute("Planes",planes);
//        return "/Admin/flights";
//    }

    // Admin only can access this method
    @PutMapping("/admin/updateplane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updatePlane(@RequestBody Planedto planedto) {
        return planeService.updatePlane(planedto);
    }

    // Admin only can access this method
    @DeleteMapping("/admin/deletePlaneById")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deletePlaneById(@RequestParam("id") int id) {
        return planeService.deletePlaneById(id);
    }

    // Admin only can access this method
    @GetMapping("/admin/getAllUsers")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("Users", users);
        for (User user : users) {
            System.out.println(user.getEmail());
        }
        return null;
    }

//    @GetMapping("/admin/getAllUsers")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    public String getAllUsers(Model model) {
//        List<User> users = userService.getAllUsers();
//        model.addAttribute("Users", users);
//        return "/Admin/user"
//    }

    // Admin only can access this method
    @PostMapping("/admin/register")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerAdmin(request));
    }

    // Admin only can access this method
    @GetMapping("/admin/getAllbooking")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getAllBooking(Model model) {
       List<Booking> bookings=bookingService.getAllBooking();
       model.addAttribute("bookings",bookings);
        for (Booking booking:bookings) {
            System.out.println(booking.getId());
        }
       return null;
    }

//    @GetMapping("/admin/getAllbooking")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    public String getAllBooking(Model model) {
//        List<Booking> bookings=bookingService.getAllBooking();
//        model.addAttribute("bookings",bookings);
//        return "/Admin/booking";
//    }


    // User only can access this method
    @GetMapping("/checkPrice")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<Double> checkPrice(@RequestBody BookingDto bookingDto) {
        return bookingService.checkPrice(bookingDto);
    }

    // User only can access this method
    @PostMapping("/checkPrice/addBooking")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<String> addBooking(@RequestBody BookingDto bookingdto) {
        return bookingService.addBooking(bookingdto);
    }

}