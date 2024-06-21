package elemental.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import elemental.domain.FileProcess;

public interface FileProcesRepository extends JpaRepository<FileProcess, Long> {

}
