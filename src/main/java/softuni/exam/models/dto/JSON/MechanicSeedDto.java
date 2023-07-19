package softuni.exam.models.dto.JSON;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MechanicSeedDto {
    @Expose
    @NotNull
    @Email
    private String email;

    @Expose
    @NotNull
    @Size(min = 2)
    private String firstName;

    @Expose
    @NotNull
    @Size(min = 2)
    private String lastName;

    @Expose
    @NotNull
    @Size(min = 2)
    private String phone;
}
