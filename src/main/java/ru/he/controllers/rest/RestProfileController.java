//package ru.he.controllers.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import ru.he.dto.UserDto;
//import ru.he.models.entities.FileInfo;
//import ru.he.security.details.UserDetailsImpl;
//import ru.he.services.FileInfoService;
//
//import static ru.he.dto.UserDto.from;
//
//@RestController
//@Profile("rest")
//public class RestProfileController {
//
//    @Autowired
//    private FileInfoService fileInfoService;
//
//    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<FileInfo> uploadFileView(@RequestParam("file") MultipartFile multipartFile, Authentication authentication) {
//        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
//        UserDto user = from(details.getUser());
//        String email = user.getEmail();
//
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setMultipartFile(multipartFile);
//        fileInfo.setEmail(email);
//
//        fileInfoService.save(fileInfo);
//        return ResponseEntity.ok(fileInfoService.save(fileInfo));
//    }
//
//    @GetMapping("/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> saveFile(@PathVariable String filename) {
//        FileInfo fileInfo = fileInfoService.getFileInfoByStorageFilename(filename);
//        Resource resource = fileInfoService.getResourceByFileInfo(fileInfo);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getOriginalFileName() + "\"")
//                .body(resource);
//    }
//}
