package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task extends BaseEntity {
    private BigDecimal price;
    private LocalDateTime date;
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private Part part;
    @ManyToOne
    private Car car;
}
