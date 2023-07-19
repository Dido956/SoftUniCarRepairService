package softuni.exam.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private BigDecimal price;
    private MechanicBasicInfo mechanic;
    private CarBasicInfo car;

    @Override
    public String toString() {
        String FORMAT = "Car %s %s with %dkm\n" +
                "-Mechanic: %s %s - task №%d:\n" +
                "--Engine: %.2f\n" +
                "---Price: %s$\n";
        return String.format(FORMAT,
                this.car.getCarMake(),
                this.car.getCarModel(),
                this.car.getKilometers(),
                this.mechanic.getFirstName(),
                this.mechanic.getLastName(),
                this.id,
                this.car.getEngine(),
                this.getPrice().setScale(2));
    }
}
