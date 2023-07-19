package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car extends BaseEntity {
    @Column
    private String type;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private Integer year;
    @Column(name = "plate_number")
    private String plateNumber;
    @Column
    private Integer kilometers;
    @Column
    private Double engine;
}
