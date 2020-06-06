package br.com.bertino.backend.repository;

import br.com.bertino.backend.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
