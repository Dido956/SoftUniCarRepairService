package softuni.exam.models.dto.XML;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class TasksSeedRootDto {

    @XmlElement(name = "task")
    private List<TaskSeedDto> tasks;

}
