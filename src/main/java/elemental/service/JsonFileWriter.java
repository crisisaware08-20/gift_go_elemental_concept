package elemental.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import elemental.domain.OutputEntry;

public class JsonFileWriter {

	ObjectMapper objectMapper = new ObjectMapper();

    public File write(List<OutputEntry> outPutEntries) {


		try {
			File tempFile = File.createTempFile("output", ".json");
			objectMapper.writeValue(tempFile, outPutEntries);
			// Delete the file on JVM exit
			// tempFile.deleteOnExit();
			return tempFile;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

    }

}

