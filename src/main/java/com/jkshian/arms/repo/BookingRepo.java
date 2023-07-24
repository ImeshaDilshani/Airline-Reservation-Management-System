package com.jkshian.arms.repo;

import com.jkshian.arms.entity.AirPlane;
import com.jkshian.arms.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Integer> {



}
