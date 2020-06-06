package br.com.bertino.backend.controller;

import br.com.bertino.backend.config.UploadFileDB;
import br.com.bertino.backend.model.ArchiveLog;
import br.com.bertino.backend.repository.ArchiveRepository;
import br.com.bertino.backend.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;
    @Autowired
    ArchiveRepository archiveRepository;

    ArchiveController(ArchiveService archiveService, ArchiveRepository archiveRepository) {
        this.archiveService = archiveService;
        this.archiveRepository = archiveRepository;
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") final MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                File logFile = File.createTempFile("access", ".log");
                FileOutputStream fos = new FileOutputStream(logFile);
                fos.write(file.getBytes());
                fos.close();
                UploadFileDB uploadFileDB = new UploadFileDB();
                List<ArchiveLog> lista = uploadFileDB.readLog(logFile);
                archiveRepository.saveAll(lista);
                return "Upload realizado com sucesso!";
            } catch (Exception e) {
                return "Falha do upload => " + e.getMessage();
            }
        } else {
            return "Upload falhou pois arquivo esta vazio.";
        }
    }

    @GetMapping(path = "/findAll")
    public List findAll() {
        return archiveRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return archiveRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/create", method =  RequestMethod.POST)
    public ArchiveLog create(@Valid @RequestBody ArchiveLog archiveLog)
    {
        return archiveRepository.save(archiveLog);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ArchiveLog archiveLog) {
        return archiveRepository.findById(id)
                .map(record -> {
                    record.setData(archiveLog.getData());
                    record.setIp(archiveLog.getIp());
                    record.setRequisicao(archiveLog.getRequisicao());
                    record.setStatus(archiveLog.getRequisicao());
                    record.setUserAgent(archiveLog.getUserAgent());
                    ArchiveLog updated = archiveRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{/id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return archiveRepository.findById(id)
                .map(record -> {
                    archiveRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
