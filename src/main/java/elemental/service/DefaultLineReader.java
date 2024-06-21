package elemental.service;

import elemental.domain.InputEntry;
import elemental.domain.LineValidationException;
import java.util.UUID;

public class DefaultLineReader {

	private String COLUMN_SEPARATOR = "\\|";

	private static enum COLUMNS {
		UUUID(0),
		ID(1),
		NAME(2),
		LIKES(3),
		TRANSPORT(4),
		AVG_SPEED(5),
		TOP_SPEED(6);

		COLUMNS(int position) {
			this.position = position;
		}

		int position;

		public int columnIndex() {
			return position;
		}
	}

	public InputEntry convertToInputEntry(String line) {

		String[] lineColumns = line.split(COLUMN_SEPARATOR);

		if (lineColumns.length != COLUMNS.values().length) {
			throw new LineValidationException(
					"Invalid line detected, ... expected this and that but got this and that ...");
		}

		try {
			InputEntry inputEntry = InputEntry.builder()
					.uuid(UUID.fromString(lineColumns[COLUMNS.UUUID.columnIndex()]))
					.id(lineColumns[COLUMNS.ID.columnIndex()])
					.name(lineColumns[COLUMNS.NAME.columnIndex()])
					.likes(lineColumns[COLUMNS.LIKES.columnIndex()])
					.transport(lineColumns[COLUMNS.TRANSPORT.columnIndex()])
					.avgSpeed(
							Double.valueOf(lineColumns[COLUMNS.AVG_SPEED.columnIndex()]))
					.topSpeed(
							Double.valueOf(lineColumns[COLUMNS.TOP_SPEED.columnIndex()]))
					.build();
			return inputEntry;

		} catch (NumberFormatException e) {
			throw new LineValidationException("Invalid speed data");
		}
	}
}
