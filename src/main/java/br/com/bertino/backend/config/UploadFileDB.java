package br.com.bertino.backend.config;

import br.com.bertino.backend.model.ArchiveLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UploadFileDB {

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public List<ArchiveLog> readLog(File logFile) {
        List<ArchiveLog> lista = new ArrayList<>();
        try (FileReader fr = new FileReader(logFile);
            BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            while (line != null) {
                ArchiveLog archiveLog = getArchiveLog(line);
                lista.add(archiveLog);
                line = br.readLine();
            }
            return lista;
        } catch (IOException | ParseException ex) {
            return null;
        }
    }

    private ArchiveLog getArchiveLog(String line) throws ParseException {
        ArchiveLog archiveLog = new ArchiveLog();
        String[] logLine = line.split("\\|");
        archiveLog.setData(formatter.parse(logLine[0]));
        archiveLog.setIp(logLine[1]);
        archiveLog.setRequisicao(logLine[2]);
        archiveLog.setStatus(logLine[3]);
        archiveLog.setUserAgent(logLine[4]);
        return archiveLog;
    }

}
