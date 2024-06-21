package elemental.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import elemental.clients.IpApiClient;
import elemental.domain.FileProcess;
import elemental.domain.InputEntry;
import elemental.domain.OutputEntry;
import elemental.persistence.jpa.FileProcesRepository;

public class FileProcessService {

  private IpApiClient ipApiClient;
  private boolean ipValidation;
  private JsonFileWriter jsonFileWriter;

  private InputConverter inputConverter;
  private FileProcesRepository fileProcesRepository;
  private FileLineReader csvFileReader;

  public FileProcessService(FileProcesRepository fileProcesRepository,
                            IpApiClient ipApiClient,
                            JsonFileWriter jsonFileWriter,
                            FileLineReader csvFileReader, boolean ipValidation) {
    this.fileProcesRepository = fileProcesRepository;
    this.ipApiClient = ipApiClient;
    this.jsonFileWriter = jsonFileWriter;
    this.csvFileReader = csvFileReader;
    this.ipValidation = ipValidation;

    this.inputConverter = new InputConverter();
  }

  public List<OutputEntry> process(MultipartFile multipartFile) {

    long startTime = System.nanoTime();

		if(ipValidation) {
			ipApiClient.getIpInfo("ip");
		}

    List<InputEntry> inputEntries = csvFileReader.read(multipartFile);

    List<OutputEntry> outPutEntries = inputEntries.stream()
                                          .map(inputConverter::convert)
                                          .collect(Collectors.toList());

    jsonFileWriter.write(outPutEntries);

    long endTime = System.nanoTime();
    double elapsedTimeInMillis = (endTime - startTime) / 1_000_000.0;

    fileProcesRepository.save(FileProcess.builder()
                                  .requestUri("requestUri")
                                  .ipAddress("ipAddress")
                                  .countryCode("coutrycode")
                                  .requestReachedApp(LocalDateTime.now())
                                  .timeLapsed(elapsedTimeInMillis)
                                  .build());

    return outPutEntries;
  }
}
