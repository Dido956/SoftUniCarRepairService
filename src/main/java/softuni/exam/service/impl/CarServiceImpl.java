package softuni.exam.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import javax.xml.bind.JAXBException;
import java.io.IOException;
// TODO: Implement all methods
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private static String CARS_FILE_PATH = "";
    private final CarRepository carRepository;
    private final ValidationUtil validationUtil;

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return null;
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        return null;
    }
}
