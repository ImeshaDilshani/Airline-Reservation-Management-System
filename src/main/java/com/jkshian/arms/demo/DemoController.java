package com.jkshian.arms.demo;

import com.jkshian.arms.dto.AuthenticationResponse;
import com.jkshian.arms.dto.BookingDto;
import com.jkshian.arms.dto.Planedto;
import com.jkshian.arms.dto.RegisterRequest;
import com.jkshian.arms.entity.AirPlane;
import com.jkshian.arms.entity.Booking;
import com.jkshian.arms.entity.User;
import com.jkshian.arms.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return  PlaneService.getPlaneById(id);
    }

    // Admin only can access this method
    @GetMapping("/admin/getAllPlane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public List<Planedto> getAllPlane(){
        return  PlaneService.getAllPlane();
    }

    // Admin only can access this method
    @PutMapping("/admin/updateplane")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updatePlane(@RequestBody Planedto planedto){
        return  PlaneService.updatePlane(planedto);
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

    @GetMapping("/Admin/")
    public ModelAndView abc (Model model){
        model.addAttribute("stuList", PlaneService.getAllPlane());

        return new ModelAndView("/Admin/flights");
    }

    @GetMapping("/Admin/new flight")
    public ModelAndView xyz( Model m){
        ModelAndView view = new ModelAndView();
        view.addObject("stu", new AirPlane());
        return view;
    }

    @PostMapping("/User/formSubmit")
    public ModelAndView efd (@ModelAttribute Planedto stu, Model model){
        PlaneService.updatePlane(stu);
        return new ModelAndView("/redirect");
    }

    @RequestMapping("/")
    public String selectFlight() {
        return "/User/select"; // Return the HTML page with the form
    }

    @PostMapping("/processSelection")
    public String processSelection(String selectedRow) {
        // Here, selectedRow will contain the value of the selected radio button
        // You can perform further processing or redirect to another page
        // For simplicity, we'll just print the selected value
        System.out.println("Selected Row: " + selectedRow);
        return "redirect:/User/ticket"; // Redirect to a result page
    }



}
