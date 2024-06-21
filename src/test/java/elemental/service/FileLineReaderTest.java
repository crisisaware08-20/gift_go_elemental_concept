package elemental.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import elemental.domain.InputEntry;

public class FileLineReaderTest {

	FileLineReader fileReader = new FileLineReader(new DefaultLineReader());

	@Test
	void it_should_read_file_content_to_inputEntries() {

		String givenFileContent = """
		18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1
		3ce2d17b-e66a-4c1e-bca3-40eb1c9222c7|2X2D24|Mike Smith|Likes Grape|Drives an SUV|35.0|95.5
		1afb6f5d-a7c2-4311-a92d-974f3180ff5e|3X3D35|Jenny Walters|Likes Avocados|Rides A Scooter|8.5|15.3
		""";

		MultipartFile multipartFile = new MockMultipartFile("EntryFile", "EntryFile.txt", "text/plain", givenFileContent.getBytes());

		List<InputEntry> actualInputEntries = fileReader.read(multipartFile);
		System.out.println("Input Entries!");
		System.out.println(actualInputEntries);

		Assertions.assertTrue(expectedInputEntries().containsAll(actualInputEntries));
	}

	private List<InputEntry> expectedInputEntries() {

		return List.of(

			InputEntry.builder()
			.uuid(UUID.fromString("18148426-89e1-11ee-b9d1-0242ac120002"))
			.id("1X1D14")
			.name("John Smith")
			.likes("Likes Apricots")
			.transport("Rides A Bike")
			.avgSpeed(6.2)
			.topSpeed(12.1)
			.build(),

			InputEntry.builder()
			.uuid(UUID.fromString("3ce2d17b-e66a-4c1e-bca3-40eb1c9222c7"))
			.id("2X2D24")
			.name("Mike Smith")
			.likes("Likes Grape")
			.transport("Drives an SUV")
			.avgSpeed(35.0)
			.topSpeed(95.5)
			.build(),

			InputEntry.builder()
			.uuid(UUID.fromString("1afb6f5d-a7c2-4311-a92d-974f3180ff5e"))
			.id("3X3D35")
			.name("Jenny Walters")
			.likes("Likes Avocados")
			.transport("Rides A Scooter")
			.avgSpeed(8.5)
			.topSpeed(15.3)
			.build()

		);
	}

}
