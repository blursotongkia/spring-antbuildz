package com.smu.antisocial.Equipment;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "api/v1/equipment")
@AllArgsConstructor
public class EquipmentController {
    @Autowired
    private final EquipmentService equipmentService;

    @GetMapping
	public List<Equipment> getEquipments(){
        return equipmentService.getEquipments();
	}

    @GetMapping(path="/equipment/{equipmentID}")
    public Optional<Equipment> getEquipment(@PathVariable("equipmentID") Integer equipmentID){
        return equipmentService.findEquipmentById(equipmentID);
    }

    @GetMapping(path="/equipmentName")
    public Optional<Equipment> findEquipmentByName(String equipmentName){
        return equipmentService.findEquipmentByName(equipmentName);
    }

    @GetMapping(path="/dimensions")
    public List<Equipment> findEquipmentByDimensions(
        @RequestParam double height,
        @RequestParam double width, 
        @RequestParam double length)
    {
        return equipmentService.findEquipmentByDimensions(height, width, length);
    }

    @GetMapping(path="/name/dimensions")
    public List<Equipment> findEquipmentByNameAndDimensions(
        @RequestParam String equipmentName,
        @RequestParam double height,
        @RequestParam double width, 
        @RequestParam double length)
    {
        return equipmentService.findEquipmentByNameAndDimensions(equipmentName, height, width, length);
    }

    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment){
        return equipmentService.createEquipment(equipment);
    }

    @DeleteMapping(path="{equipmentID}")
    public void deleteEquipment(@PathVariable("equipmentID") Integer equipmentID){
        equipmentService.deleteEquipment(equipmentID);
    }

}
