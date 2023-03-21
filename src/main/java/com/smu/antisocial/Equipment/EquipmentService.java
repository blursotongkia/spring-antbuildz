package com.smu.antisocial.Equipment;

import java.util.*;

import com.smu.antisocial.Transport.TransportRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipmentService {

    @Autowired
    private final EquipmentRepository equipmentRepository;

    @Autowired
    private final TransportRepository transportRepository;

    public List<Equipment> getEquipments(){
        return equipmentRepository.findAll();
	  }

    public Equipment createEquipment(Equipment equipment){
        Integer transportID = equipment.getTransportID();
        transportRepository.findById(transportID).orElseThrow(() -> new IllegalStateException("Transport with ID " + transportID + " does not exist"));
        
		return equipmentRepository.save(equipment);
	}

    public void deleteEquipment(Integer equipmentid){
        boolean exists = equipmentRepository.existsById(equipmentid);
		if(!exists){
			throw new IllegalStateException("Equipment with ID " + equipmentid + " does not exist" );
		}
		equipmentRepository.deleteById(equipmentid);
	}

    public Optional<Equipment> findEquipmentByName(String equipmentName){
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByName(equipmentName);
		if (equipmentOptional.isEmpty()){
			throw new IllegalStateException("Equipment not found");
		}
        else{
            return equipmentOptional;
        }
    }

    public Optional<Equipment> findEquipmentById(Integer equipmentId){
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByID(equipmentId);
		if (equipmentOptional.isEmpty()){
			throw new IllegalStateException("Equipment not found");
		}
        else{
            return equipmentOptional;
        }
    }

    public List<Equipment> findEquipmentByDimensions(double height, double width , double length){
        double percentage = 0.1; //deviation of 10% of the input dimensions
        double heightMin = height - (height / percentage);
        double heightMax = height + (height / percentage);
        double widthMin = width - (width / percentage);
        double widthMax = width + (width / percentage);
        double lengthMin = length - (length / percentage);
        double lengthMax = length + (length / percentage);
        List<Equipment> equipmentList = equipmentRepository.findEquipmentByDimensions(heightMin, heightMax, widthMin,widthMax,lengthMin, lengthMax);
        return equipmentList;
    }

    public List<Equipment> findEquipmentByNameAndDimensions(String equipmentName, double height, double width , double length){
        double percentage = 0.1; //deviation of 10% of the input dimensions
        double heightMin = height - (height / percentage);
        double heightMax = height + (height / percentage);
        double widthMin = width - (width / percentage);
        double widthMax = width + (width / percentage);
        double lengthMin = length - (length / percentage);
        double lengthMax = length + (length / percentage);
        List<Equipment> equipmentList = equipmentRepository.findEquipmentByNameAndDimensions(equipmentName,heightMin, heightMax, widthMin,widthMax,lengthMin, lengthMax);
        return equipmentList;
    }
    
}
