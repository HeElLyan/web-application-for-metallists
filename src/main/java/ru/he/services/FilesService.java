package ru.he.services;

import ru.he.dto.InformationDto;

public interface FilesService {
    void init();

    void convert();

    void convertPngByUser(Long userId);

//    InformationDto getInformation(Long userId);
}
