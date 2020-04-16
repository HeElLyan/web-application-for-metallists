package ru.he.models.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "file_info")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // владелец файла
    @Column(name = "email")
//    @Transient
    private String email;
    // multipart
    @Transient
    private MultipartFile multipartFile;
    // название файла в хранилище
    @Column(name = "storageFileName")
    private String storageFileName;
    // название файла оригинальное
    @Column(name = "originalFileName")
    private String originalFileName;
    // размер файла
    @Column(name = "size")
    private Long size;
    // тип файла (MIME)
    @Column(name = "type")
    private String type;
    // по какому URL можно получить файл
    @Column(name = "path")
    private String path;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

}
