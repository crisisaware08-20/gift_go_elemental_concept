package elemental.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import elemental.domain.InputEntry;

public class FileLineReader {

	private DefaultLineReader lineReader;

	public FileLineReader(DefaultLineReader defaultFileFormat) {
		this.lineReader = defaultFileFormat;
	}

	public List<InputEntry> read(MultipartFile multipartFile) {

		List<InputEntry> inputEntries = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(multipartFile.getInputStream()))) {

			String line;

			while ((line = bufferedReader.readLine()) != null) {

				InputEntry inputEntry = lineReader.convertToInputEntry(line);

				inputEntries.add(inputEntry);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return inputEntries;
	}

}
