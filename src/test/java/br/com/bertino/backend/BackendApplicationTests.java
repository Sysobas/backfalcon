package br.com.bertino.backend;

import br.com.bertino.backend.controller.ArchiveController;
import br.com.bertino.backend.service.ArchiveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    ArchiveController controller;

    @Autowired
    ArchiveService archiveService;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void findAll() throws Exception {
        assertThat(controller.findAll()).isEmpty();
    }
}