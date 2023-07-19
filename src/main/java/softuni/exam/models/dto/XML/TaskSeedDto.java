package softuni.exam.models.dto.XML;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class TaskSeedDto {
    @XmlElement
    @NotNull
    private String date;

    @XmlElement
    @NotNull
    @Positive
    private BigDecimal price;

    @XmlElement
    @NotNull
    private CarIdDto car;

    @XmlElement
    @NotNull
    private MechanicFirstNameDTO mechanic;

    @XmlElement
    @NotNull
    private PartNameDto part;
}
