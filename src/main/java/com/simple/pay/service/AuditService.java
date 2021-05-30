package com.simple.pay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.pay.model.dto.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class AuditService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${audit.json.path}")
    private String auditPath;

    @PostConstruct
    public void init() throws IOException {
        Path path = Path.of(auditPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
    }

    synchronized public void audit(PaymentDto dto) {
        try {
            Files.writeString(Path.of(auditPath), mapper.writeValueAsString(dto) + "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error("Cannot save the record into audit file", e);
        }
    }

    public Stream<String> readAsStream() {
        try {
            return Files.lines(Path.of(auditPath));
        } catch (IOException e) {
            log.error("Cannot read audit file", e);
            return Stream.empty();
        }
    }
}
