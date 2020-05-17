package ru.he.services.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.he.dto.InformationDto;
import ru.he.models.entities.Document;
import ru.he.models.entities.User;
import ru.he.repositoriesJpa.DocumentsRepository;
import ru.he.repositoriesJpa.UsersRepository;
import ru.he.services.FilesService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Component
@Slf4j
public class FilesServiceImpl implements FilesService {

    private final static String FILES_PATH = "/Users/heel/IdeaProjects/2Course/4sem-spring/MyProjects/WebApplicationForMetallists/src/main/webapp/WEB-INF/img";
//    private final static String FILES_PATH = "/Users/heel/IdeaProjects/2Course/4sem-spring/MyProjects/WebApplicationForMetallists/files";
    private final static String CONVERTED_FILES_PATH = "/Users/heel/IdeaProjects/2Course/4sem-spring/MyProjects/WebApplicationForMetallists/src/main/webapp/WEB-INF/img/converted";
//    private final static String CONVERTED_FILES_PATH = "/Users/heel/IdeaProjects/2Course/4sem-spring/MyProjects/WebApplicationForMetallists/converted_files";

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DocumentsRepository documentsRepository;

    @Override
    public void init() {
        User admin = usersRepository.getOne(2L);

        try (Stream<Path> filesPaths = Files.walk(Paths.get(FILES_PATH))) {
            filesPaths.filter(filePath -> filePath.toFile().isFile()).forEach(
                    filePath -> {
                        File file = filePath.toFile();
                        Document document = null;
                        try {
                            document = Document.builder()
                                    .user(admin)
                                    .path(filePath.toString())
                                    .size(file.length())
                                    .type(Files.probeContentType(filePath))
                                    .build();
                        } catch (IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                        documentsRepository.save(document);
                    }
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Transactional
    @Override
    public void convert() {
        List<Document> documents = documentsRepository.findAll();

        for (Document document : documents) {
            String newFileName = CONVERTED_FILES_PATH + "/" + document.getFileName() + ".jpg";

            if (document.getType().equals("application/pdf")) {
                convertPdfToJpg(document, newFileName);
                document.setType("image/jpeg");
//                documentsRepository.save(document);
            }
        }
    }

    @Transactional
    @Override
    public void convertPngByUser(Long userId) {
        User admin = usersRepository.getOne(userId);
        List<Document> documents = admin.getPngDocuments();

        for (Document document : documents) {
            String newFileName = CONVERTED_FILES_PATH + "/" + document.getFileName() + ".jpg";

            convertPngToJpg(document, newFileName);
            document.setType("image/jpeg");
        }

        log.info("Get png - " + documents.size());
    }

//    @Override
//    public InformationDto getInformation(Long userId) {
//        return usersRepository.getInformationByUser(userId);
//    }

    @SneakyThrows
    private void convertPngToJpg(Document document, String newFileName) {
        BufferedImage image = ImageIO.read(document.getSourceFile());
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        result.createGraphics().drawImage(image, 0, 0, Color.BLACK, null);
        File resultFile = new File(newFileName);
        ImageIO.write(result, "jpg", resultFile);
        document.setSourceFile(resultFile);
    }

    @SneakyThrows
    private void convertPdfToJpg(Document document, String newFileName) {
        PDDocument pdf = PDDocument.load(document.getSourceFile());
        PDFRenderer renderer = new PDFRenderer(pdf);
        BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
        ImageIOUtil.writeImage(image, newFileName, 300);
        pdf.close();
        File resultFile = new File(newFileName);
        document.setSourceFile(resultFile);
    }
}
