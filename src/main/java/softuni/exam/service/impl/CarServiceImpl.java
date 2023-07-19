package softuni.exam.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.XML.CarSeedDto;
import softuni.exam.models.dto.XML.CarsSeedRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static softuni.exam.util.Constants.*;

// TODO: Implement all methods
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;


    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CAR_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        List<CarSeedDto> cars = xmlParser.fromFile(CAR_FILE_PATH, CarsSeedRootDto.class).getCars();

        for (CarSeedDto car : cars) {
            sb.append(System.lineSeparator());

            if(!validationUtil.isValid(car) || carRepository.findFirstByPlateNumber(car.getPlateNumber()).isPresent()){
                sb.append(INVALID_CAR);
                continue;
            }

            sb.append(String.format(VALID_CAR,car.getCarMake(),car.getCarModel()));
            carRepository.save(modelMapper.map(car, Car.class));
        }
        return sb.toString();
    }
}
