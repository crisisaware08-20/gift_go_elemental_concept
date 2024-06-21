package elemental.service;

import elemental.domain.InputEntry;
import elemental.domain.LineValidationException;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultLineReaderTest {

	@Test
	void should_create_inputEntry_from_the_line() {

		String line = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1";

		var expetedInputEntry = InputEntry.builder()
				.uuid(UUID.fromString("18148426-89e1-11ee-b9d1-0242ac120002"))
				.id("1X1D14")
				.name("John Smith")
				.likes("Likes Apricots")
				.transport("Rides A Bike")
				.avgSpeed(6.2)
				.topSpeed(12.1)
				.build();

		DefaultLineReader defaultLineFormat = new DefaultLineReader();

		var actualInputEntry = defaultLineFormat.convertToInputEntry(line);

		Assertions.assertEquals(expetedInputEntry, actualInputEntry);
	}

	@Test
	void it_should_not_convert_to_inputEntry_when_line_is_not_of_the_right_format() {
		String lineWithMoreDetailsAsRequired = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1|Not supposed";

		DefaultLineReader defaultLineFormat = new DefaultLineReader();

		Assertions.assertThrows(LineValidationException.class,
				() -> defaultLineFormat.convertToInputEntry(lineWithMoreDetailsAsRequired));
	}

	@Test
	void it_should_not_convert_to_inputEntry_when_line_contains_bad_data() {

		String lineWithBadDataForSpeed = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|one hundreed";

		DefaultLineReader defaultLineFormat = new DefaultLineReader();

		Assertions.assertThrows(LineValidationException.class,
				() -> defaultLineFormat.convertToInputEntry(lineWithBadDataForSpeed));

	}

}
