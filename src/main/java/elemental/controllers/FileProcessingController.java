package elemental.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import elemental.domain.OutputEntry;
import elemental.persistence.jpa.FileProcesRepository;
import elemental.service.FileProcessService;

@RestController
public class FileProcessingController {

  @Autowired FileProcesRepository repository;

  @Autowired FileProcessService fileProcessService;

  @GetMapping(value = "/hello")
  public String hello() {
    return "Up!";
  }

  @PostMapping(value = "/process")
  public ResponseEntity<List<OutputEntry>>
  processFile(@RequestParam("file") MultipartFile multiPartFile) {

    List<OutputEntry> outputEntries = fileProcessService.process(multiPartFile);

    return ResponseEntity.status(201).body(outputEntries);
  }
}
