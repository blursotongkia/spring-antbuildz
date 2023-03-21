package com.smu.antisocial.Equipment;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository 
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    @Query("SELECT e FROM Equipment e WHERE e.equipmentName = ?1")
    Optional<Equipment> findEquipmentByName(String equipmentName);

    @Query("SELECT e FROM Equipment e WHERE e.equipmentID = ?1")
    Optional<Equipment> findEquipmentByID(Integer equipmentID);

    @Query("SELECT e FROM Equipment e WHERE e.height BETWEEN ?1 and ?2 AND e.width BETWEEN ?3 and ?4 AND e.length BETWEEN ?5 and ?6")
    List<Equipment> findEquipmentByDimensions(double heightMin,double heightMax, double widthMin, double widthMax,double lengthMin, double lengthMax);

    @Query("SELECT e FROM Equipment e WHERE e.equipmentName LIKE %?1% AND e.height BETWEEN ?2 and ?3 AND e.width BETWEEN ?4 and ?5 AND e.length BETWEEN ?6 and ?7")
    List<Equipment> findEquipmentByNameAndDimensions(String equipmentName, double heightMin,double heightMax, double widthMin, double widthMax,double lengthMin, double lengthMax);

}
