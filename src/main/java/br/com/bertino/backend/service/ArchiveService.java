package br.com.bertino.backend.service;

import br.com.bertino.backend.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ArchiveService {

    @Autowired
    ArchiveRepository archiveRepository;

    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    public List findAll() {
        return archiveRepository.findAll();
    }

    public List findByIp(@PathVariable String ip) {
        return archiveRepository.findByIp(ip);
    }

    public List findByRequisicao(@PathVariable String requisicao) {
        return archiveRepository.findByRequisicao(requisicao);
    }

    public List findByStatus(@PathVariable String status) {
        return archiveRepository.findByStatus(status);
    }

    public List findByUserAgent(@PathVariable String userAgent) {
        return archiveRepository.findByUserAgent(userAgent);
    }

}
