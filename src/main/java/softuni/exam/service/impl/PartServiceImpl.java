package softuni.exam.service.impl;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.gson.PartsSeedDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartRepository;
import softuni.exam.service.PartService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.util.Constants.PARTS_FILE_PATH;

@Service
@AllArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Override
    public boolean areImported() {
        return partRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files
                .readString(Path.of(PARTS_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {
        StringBuilder sb = new StringBuilder();

        List<PartsSeedDto> parts = Arrays.stream(gson.fromJson(readPartsFileContent(), PartsSeedDto[].class)).collect(Collectors.toList());

        for (PartsSeedDto part : parts) {
            sb.append(System.lineSeparator());

            if(this.partRepository.findFirstByPartName(part.getPartName()).isPresent() || !validationUtil.isValid(part)){
                sb.append("Invalid part");
                continue;
            }

            sb.append(String.format("Successfully added %s - %.2f", part.getPartName(),part.getPrice()));

            this.partRepository.save(this.modelMapper.map(part, Part.class));
        }

        System.out.println();
        return sb.toString();
    }
}
