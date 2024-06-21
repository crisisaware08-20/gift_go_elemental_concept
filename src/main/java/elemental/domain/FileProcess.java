package elemental.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file_process")
public class FileProcess {
	@Id
	@Column(name = "request_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID requestId;
	@Column(name = "request_uri")
	String requestUri;
	@Column(name = "ip")
	String ipAddress;
	@Column(name = "country_code")
	String countryCode;
	@Column(name = "request_reached_app")
	LocalDateTime requestReachedApp;
	@Column(name = "time_lapsed")
	Double timeLapsed;
}
