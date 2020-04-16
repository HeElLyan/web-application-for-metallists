package ru.he.services;

import org.springframework.core.io.Resource;
import ru.he.models.entities.FileInfo;

public interface FileInfoService {

    FileInfo getFileInfoByStorageFilename(String url);

    Resource getResourceByFileInfo(FileInfo fileInfo);

    void save(FileInfo fileInfo);

}
