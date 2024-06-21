package elemental.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import elemental.domain.OutputEntry;

public class JsonFileWriterTest {

	JsonFileWriter jsonFileWrite = new JsonFileWriter();

	@Test
	void it_should_write_list_of_outputEntries_to_json_file() {

		var outPutEntries = List.of(
				OutputEntry.builder()
						.name("Jhon")
						.transport("bike")
						.topSpeed(32.5)
						.build(),

				OutputEntry.builder()
						.name("Mihail")
						.transport("bike")
						.topSpeed(65.5)
						.build());

		File jsonFile = jsonFileWrite.write(outPutEntries);

		Assertions.assertTrue(outPutEntries.containsAll(readContentFrom(jsonFile)));

	}

	private List<OutputEntry> readContentFrom(File jsonFile) {
		List<OutputEntry> outputEntries = null;
		try {
			var objectMapper = new ObjectMapper();
			outputEntries = objectMapper.readValue(jsonFile, new TypeReference<List<OutputEntry>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputEntries;
	}

}
