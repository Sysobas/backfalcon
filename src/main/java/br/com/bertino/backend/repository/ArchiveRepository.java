package br.com.bertino.backend.repository;

import br.com.bertino.backend.model.ArchiveLog;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ArchiveRepository extends JpaRepository<ArchiveLog, Long> {

    List<ArchiveLog> findByIp(String ip);
    List<ArchiveLog> findByRequisicao(String requisicao);
    List<ArchiveLog> findByStatus(String status);
    List<ArchiveLog> findByUserAgent(String userAgent);
}
