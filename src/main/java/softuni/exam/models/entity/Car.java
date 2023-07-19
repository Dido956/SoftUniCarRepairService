package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarType type;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column
    private Integer year;
    @Column(name = "plate_number",unique = true)
    private String plateNumber;
    @Column
    private Integer kilometers;
    @Column
    private Double engine;
}
