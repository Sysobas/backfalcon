package br.com.bertino.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="access")
@Data
public class ArchiveLog {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="Data")
    private Date data;
    @Column(name="IP")
    private String ip;
    @Column(name="Request")
    private String requisicao;
    @Column(name="Status")
    private String status;
    @Column(name="User_Agent")
    private String userAgent;
}
