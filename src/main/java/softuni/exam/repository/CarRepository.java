package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findFirstByPlateNumber(String plateNumber);

    @Query(value = "SELECT make,model,first_name,last_name,t.id,engine,price\n" +
            "FROM cars\n" +
            "JOIN tasks t ON cars.id = t.car_id\n" +
            "JOIN mechanics m ON m.id = t.mechanic_id\n" +
            "WHERE type = 'coupe'\n" +
            "ORDER BY price DESC;\n", nativeQuery = true)
    List<Car> findCarsByMakeModelMechanic();
}
