package elemental.service;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import elemental.domain.InputEntry;
import elemental.domain.OutputEntry;

public class InputConverterTest {

  @Test
  public void it_should_convert_inputEntry_to_outputEntry() {

    InputConverter inputConverter = new InputConverter();

    var inputEntry =
        InputEntry.builder()
            .uuid(UUID.fromString("18148426-89e1-11ee-b9d1-0242ac120002"))
            .id("1X1D14")
            .name("John Smith")
            .likes("Likes Apricots")
            .transport("Rides A Bike")
            .avgSpeed(6.2)
            .topSpeed(12.1)
            .build();

    var actualResult = inputConverter.convert(inputEntry);

    Assertions.assertEquals(OutputEntry.builder()
                                .name("John Smith")
                                .transport("Rides A Bike")
                                .topSpeed(12.1)
                                .build(),
                            actualResult);
  }
}
