package softuni.exam.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TaskDto;
import softuni.exam.models.dto.XML.TaskSeedDto;
import softuni.exam.models.dto.XML.TasksSeedRootDto;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.repository.PartRepository;
import softuni.exam.repository.TaskRepository;
import softuni.exam.service.TaskService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.util.Constants.*;

// TODO: Implement all methods
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MechanicRepository mechanicRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return taskRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASK_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        List<TaskSeedDto> tasks =
                this.xmlParser
                        .fromFile(TASK_FILE_PATH, TasksSeedRootDto.class).getTasks();

        for (TaskSeedDto task : tasks) {
            sb.append(System.lineSeparator());

            if (validationUtil.isValid(task)) {
                Optional<Mechanic> mechanic = mechanicRepository.findFirstByFirstName(task.getMechanic().getFirstName());
                Optional<Car> car = carRepository.findById(task.getCar().getId());
                Optional<Part> part = partRepository.findById(task.getPart().getId());

                if (car.isEmpty() || part.isEmpty() || mechanic.isEmpty()) {
                    sb.append(String.format(INVALID_TASK));
                    continue;
                }

                Task mappedTask = modelMapper.map(task, Task.class);
                mappedTask.setMechanic(mechanic.get());
                mappedTask.setCar(car.get());
                mappedTask.setPart(part.get());

                taskRepository.save(mappedTask);
                sb.append(String.format(VALID_TASK, task.getPrice().setScale(2), "").trim());
                continue;
            }
            sb.append(String.format(INVALID_TASK));
        }


        return sb.toString().trim();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
      return this.taskRepository
                .findAllByCarTypeOrderByPriceDesc(CarType.coupe)
                .stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .map(TaskDto::toString)
                .collect(Collectors.joining())
                .trim();

    }
}
