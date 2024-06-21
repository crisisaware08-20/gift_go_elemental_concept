package elemental.service;

import elemental.domain.InputEntry;
import elemental.domain.OutputEntry;

public class InputConverter {

	public OutputEntry convert(InputEntry inputEntry) {
		return OutputEntry.builder()
				.topSpeed(inputEntry.getTopSpeed())
				.transport(inputEntry.getTransport())
				.name(inputEntry.getName())
				.build();
	}
}
