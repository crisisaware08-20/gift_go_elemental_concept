package elemental.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import elemental.clients.IpApiClient;
import elemental.domain.FileProcess;
import elemental.domain.OutputEntry;
import elemental.persistence.jpa.FileProcesRepository;

@ExtendWith(MockitoExtension.class)
public class FileProcessServiceTest {

	@Mock
	IpApiClient ipApiClient;

	@Mock
	JsonFileWriter jsonFileWriter;

	@Mock
	FileLineReader csvFileReader;

	@Mock
	FileProcesRepository fileProcesRepository;

	static boolean IP_VALIDATION = false;

	FileProcessService fileProcessService;

	MultipartFile multipartFile;
	DefaultLineReader defaultFileFormat;

	@BeforeEach
	void set_up() {
		fileProcessService = new FileProcessService(fileProcesRepository, ipApiClient,
				jsonFileWriter, csvFileReader, IP_VALIDATION);
	}

	@Test
	void it_should_skip_validation_when_feature_flag_is_disabled() {

		Mockito.when(csvFileReader.read(multipartFile)).thenReturn(List.of());

		fileProcessService.process(multipartFile);

		Mockito.verifyNoInteractions(ipApiClient);
	}

	@Test
	void it_should_call_ipClient_when_feature_flag_is_enabled() {
		Mockito.when(csvFileReader.read(multipartFile)).thenReturn(List.of());
		fileProcessService = new FileProcessService(fileProcesRepository, ipApiClient,
				jsonFileWriter, csvFileReader, true);

		fileProcessService.process(multipartFile);

		Mockito.verify(ipApiClient).getIpInfo(Mockito.any());;
	}

	@Test
	void it_should_write_pulled_details_from_original_file_to_json_file() {
		Mockito.when(csvFileReader.read(multipartFile)).thenReturn(List.of());

		fileProcessService.process(multipartFile);

		Mockito.verify(jsonFileWriter).write(Mockito.<OutputEntry>anyList());
	}

	@Test
	void it_should_write_file_processing_details_to_database() {

		Mockito.when(csvFileReader.read(multipartFile)).thenReturn(List.of());

		fileProcessService.process(multipartFile);

		Mockito.verify(fileProcesRepository).save(Mockito.any(FileProcess.class));
	}
}
