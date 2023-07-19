package softuni.exam.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.JSON.MechanicSeedDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicRepository;
import softuni.exam.service.MechanicService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.util.Constants.*;

// TODO: Implement all methods
@Service
@AllArgsConstructor
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
        return Files.readString(Path.of(MECHANICS_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        StringBuilder sb = new StringBuilder();

        List<MechanicSeedDto> mechanics = Arrays.stream(gson.fromJson(readMechanicsFromFile(), MechanicSeedDto[].class)).collect(Collectors.toList());

        for (MechanicSeedDto mechanic : mechanics) {
            sb.append(System.lineSeparator());

            if (this.mechanicRepository.findFirstByEmail(mechanic.getEmail()).isPresent() ||
                    this.mechanicRepository.findFirstByFirstName(mechanic.getFirstName()).isPresent() ||
                    !validationUtil.isValid(mechanic)) {

                sb.append(INVALID_MECHANIC);
                continue;
            }

            sb.append(String.format(VALID_MECHANIC, mechanic.getFirstName(), mechanic.getLastName()));

            this.mechanicRepository.save(modelMapper.map(mechanic, Mechanic.class));
        }

        return sb.toString();
    }
}
