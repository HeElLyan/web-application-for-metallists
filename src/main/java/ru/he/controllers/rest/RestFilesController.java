package ru.he.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.he.dto.InformationDto;
import ru.he.services.FilesService;

@PreAuthorize("permitAll()")
@RestController
public class RestFilesController {

    @Autowired
    private FilesService filesService;

    @GetMapping("/files/init")
    public ResponseEntity<?> init() {
        filesService.init();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/files/convert")
    public ResponseEntity<?> convert() {
        filesService.convert();
        return ResponseEntity.ok().build();
    }

    @GetMapping("users/{user-id}/files/png/convert")
    public ResponseEntity<?> convertPngByUser(@PathVariable("user-id") Long userId) {
        filesService.convertPngByUser(userId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("users/{user-id}/files/information")
//    public ResponseEntity<InformationDto> getInformation(@PathVariable("user-id") Long userId) {
//        InformationDto result = filesService.getInformation(userId);
//        return ResponseEntity.ok(result);
//    }
}
