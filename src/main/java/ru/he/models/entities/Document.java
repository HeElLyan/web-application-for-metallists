package ru.he.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String path;
    private Long size;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //transient-не нужно создавать колонку в бд, относится к jpa
    @Transient
    private String fileName;

    //привзяываем к основному файлу
    @Transient
    private File sourceFile;

    //после получения из бд этой сущности
    @PostLoad
    //для иницифлизации полей type,path,size
    public void loadFile() {
        // persistent(path) -> transient(sourceFile, fileName)
        sourceFile = new File(path);
        //отрезаем все что находится после точки
        fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("Load file for " + fileName);
    }

    //вызывается перед сохранением документа в бд
    @PreUpdate
    public void updateFileInformation() {
        // transient(sourceFile) -> persistent(path, size)
        this.path = sourceFile.getPath();
        this.size = sourceFile.length();
        this.fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("Update file information for " + fileName);
    }
}
