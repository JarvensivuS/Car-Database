//Service class for H2 database SQL queries.

package com.samuli.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.samuli.project.Data.Car;
import jakarta.transaction.Transactional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{

    @Query(value = "select * from car where id = :id",nativeQuery = true)
    String findByID(Long id);
 
    @Modifying
    @Query(value = "insert into car (make,model,plate_number) values (:make,:model,:plateNumber)",nativeQuery = true)
    void addCar(@Param("make")String make,@Param("model")String model,@Param("plateNumber")String plateNumber);

    @Transactional
    @Modifying
    @Query(value = "delete from car where id = :id",nativeQuery = true)
    int deleteCar(Long id);
    

}

