package com.jkshian.arms.service;


import com.jkshian.arms.dto.BookingDto;
import com.jkshian.arms.entity.AirPlane;
import com.jkshian.arms.entity.Booking;
import com.jkshian.arms.repo.BookingRepo;
import com.jkshian.arms.repo.PlaneRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final PlaneRepo planeRepo;
    private final BookingRepo bookingRepo;


    public List<Booking> getAllBooking() {
        return bookingRepo.findAll();
    }


    public ResponseEntity<Double> checkPrice(BookingDto bookingdto) {
        AirPlane findPlane =planeIsAvilable(bookingdto);
        if(findPlane!=null){
              bookingdto.setPrice(calculatePrice(findPlane.getNumOfKm(),bookingdto.getBnumofseat()));
            return ResponseEntity.ok(bookingdto.getPrice());
          }else {
            ResponseEntity.status(401).body("Your enteres plane is not avilable");
           return null;
        }
    }

    private AirPlane planeIsAvilable(BookingDto bookingdto){
        AirPlane findPlane = planeRepo.findByStartAndEnd(bookingdto.getBstart(),bookingdto.getBend());
        if(findPlane == null){
            ResponseEntity.status(401).body("Your enteres plane is not avilable");
            return null;
        }else {
            return findPlane;
        }
    }

    private double calculatePrice(double numOfKm, int bnumOfseat) {
        double pricePerKm = 10000.00;
        return numOfKm*bnumOfseat*pricePerKm;
    }

    public ResponseEntity<String> addBooking(BookingDto bookingdto) {
        AirPlane findPlane =planeIsAvilable(bookingdto);
        Booking booking = new Booking();
        if(findPlane != null){

            booking.setPrice(calculatePrice(findPlane.getNumOfKm(),bookingdto.getBnumofseat()));
            booking.setBStart(bookingdto.getBstart());
            booking.setBEnd(bookingdto.getBend());
            booking.setBNumOfseat(bookingdto.getBnumofseat());
//            booking.setUserEmail(bookingdto.getUseremail());

           if(findPlane.getAvlSeat() - bookingdto.getBnumofseat() >= 0){
               bookingRepo.save(booking);
               findPlane.setAvlSeat(findPlane.getAvlSeat() - bookingdto.getBnumofseat());
               planeRepo.save(findPlane);
           }else {
              return ResponseEntity.status(401).body("Seats Are not Available");
           }
           return ResponseEntity.ok("We recoded your booking");
        }else {
            return ResponseEntity.status(401).body("The plane is not found");
        }
    }
}
