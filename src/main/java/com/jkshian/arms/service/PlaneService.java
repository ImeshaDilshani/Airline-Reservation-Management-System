package com.jkshian.arms.service;


import com.jkshian.arms.dto.Planedto;
import com.jkshian.arms.entity.AirPlane;
import com.jkshian.arms.entity.User;
import com.jkshian.arms.repo.PlaneRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaneService {
    private  final PlaneRepo planeRepo;
    private  final ModelMapper modelMapper;


    public ResponseEntity<String> createNewPlane(Planedto planedto){
      AirPlane airPlane = new AirPlane();
      airPlane.setStart(planedto.getStart());
      airPlane.setEnd(planedto.getEnd());
      airPlane.setAvlSeat(planedto.getAvlSeat());
      airPlane.setNumOfKm(planedto.getNumOfKm());
       if(planeRepo.equals(airPlane)){
           return ResponseEntity.status(401).body("The Plane alrady exist");
       }else {
           planeRepo.save(airPlane);
           return ResponseEntity.status(200).body("Sccessfully added");
       }
    }

    public ResponseEntity<String> updatePlane(Planedto planedto) {
       AirPlane existOne =  modelMapper.map(getPlaneById(planedto.getId()),AirPlane.class);
       if(existOne == null){
           return ResponseEntity.status(401).body("The Plane does not alrady exist");
       }else {
           existOne.setStart(planedto.getStart());
           existOne.setEnd(planedto.getEnd());
           existOne.setNumOfKm(planedto.getNumOfKm());
           existOne.setAvlSeat(planedto.getAvlSeat());
           planeRepo.save(existOne);
           return ResponseEntity.status(200).body("Sccessfully Updated");
       }
    }

    public Planedto getPlaneById(int id) {
        AirPlane existOne =  planeRepo.findById(id).orElseThrow();
        if(existOne == null){
            ResponseEntity.status(401).body("Invalid Id");
            return null;
        }else {
            ResponseEntity.status(200);
            return modelMapper.map(existOne,Planedto.class);

        }
    }


    public List<Planedto> getAllPlane() {
        List<AirPlane> airPlanes = planeRepo.findAll();
        return modelMapper.map(airPlanes, new TypeToken<List<Planedto>>(){}.getType());
    }

    public ResponseEntity<String> deletePlaneById(int id) {
        Planedto existOne = getPlaneById(id);
        planeRepo.delete(modelMapper.map(existOne,AirPlane.class));
        return ResponseEntity.status(200).body(" The AirPlane  is  Sccessfully Deleted");
    }

}
