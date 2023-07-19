package softuni.exam.models.dto.XML;

import lombok.Getter;
import lombok.Setter;
import softuni.exam.models.entity.CarType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class CarSeedDto {
    @XmlElement
    @Size(min = 2, max = 30)
    private String carMake;

    @XmlElement
    @Size(min = 2, max = 30)
    private String carModel;

    @XmlElement
    @Positive
    private Integer year;

    @XmlElement
    @Size(min = 2, max = 30)
    private String plateNumber;

    @XmlElement
    private Integer kilometers;

    @XmlElement
    @DecimalMin("1.00")
    private Double engine;

    @XmlElement
    @Enumerated(EnumType.STRING)
    private CarType carType;
}
