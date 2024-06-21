package elemental;

import elemental.clients.IpApiClient;
import elemental.persistence.jpa.FileProcesRepository;
import elemental.service.DefaultLineReader;
import elemental.service.FileLineReader;
import elemental.service.FileProcessService;
import elemental.service.JsonFileWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

	@Autowired
	FileProcesRepository fileProcesRepository;

	@Autowired
	IpApiClient ipApiClient;

	boolean IP_VALIDATION = false;

	DefaultLineReader lineFormat = new DefaultLineReader();

	@Bean
	public FileProcessService fileProcessService() {
		JsonFileWriter jsonFileWriter = new JsonFileWriter();
		FileLineReader fileLineReader = new FileLineReader(lineFormat);

		return new FileProcessService(fileProcesRepository, ipApiClient,
				jsonFileWriter, fileLineReader, IP_VALIDATION);
	}
}
