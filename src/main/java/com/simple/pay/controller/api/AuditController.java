package com.simple.pay.controller.api;

import com.simple.pay.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/v1/audit/")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> streamDataFlux() {
        return Flux.fromStream(auditService.readAsStream()).map(string -> string + "\n");
    }
}
