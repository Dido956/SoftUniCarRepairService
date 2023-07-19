package softuni.exam.models.dto.gson;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class PartsSeedDto {
    @Expose
    @NotNull
    @Size(min = 2, max = 19)
    private String partName;
    @Expose
    @DecimalMin("10.0")
    @DecimalMax("2000.00")
    private Double price;
    @Expose
    @NotNull
    @Positive
    private Integer quantity;
}
