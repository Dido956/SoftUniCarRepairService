package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "parts")
@Getter
@Setter
@NoArgsConstructor
public class Part extends BaseEntity{
    @Column(name = "part_name")
    private String partName;
    @Column
    private BigDecimal price;
    @Column
    private Integer quantity;
}
