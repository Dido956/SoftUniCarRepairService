package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.util.Constants.MECHANICS_FILE_PATH;

// TODO: Implement all methods
@Service
public class MechanicServiceImpl implements MechanicService {

    private MechanicRepository mechanicRepository;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;


    @Override
    public boolean areImported() {
        return mechanicRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANICS_FILE_PATH))
    }

    @Override
    public String importMechanics() throws IOException {
        return null;
    }
}
