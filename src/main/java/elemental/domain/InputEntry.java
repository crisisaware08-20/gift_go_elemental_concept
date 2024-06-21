package elemental.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InputEntry {
	private UUID uuid;
	private String id;
	private String name;
	private String likes;
	private String transport;
	private double avgSpeed;
	private double topSpeed; 
}
