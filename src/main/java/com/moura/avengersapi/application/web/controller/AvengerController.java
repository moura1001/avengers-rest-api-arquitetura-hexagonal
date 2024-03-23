package com.moura.avengersapi.application.web.controller;

import com.moura.avengersapi.application.web.request.AvengerReq;
import com.moura.avengersapi.application.web.response.AvengerResp;
import com.moura.avengersapi.domain.avenger.Avenger;
import com.moura.avengersapi.domain.avenger.AvengerStorage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/avengers")
public class AvengerController {

    @Autowired
    private AvengerStorage avengerStorage;

    @GetMapping
    public ResponseEntity<List<AvengerResp>> getAvengers() {
        List<AvengerResp> avengers = avengerStorage.getAvengers().stream()
                .map((avenger) -> new AvengerResp(avenger)).collect(Collectors.toList());
        return ResponseEntity.ok(avengers);
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<AvengerResp> getAvengerById(@PathVariable Long id) {
        Avenger a = avengerStorage.getAvengerById(id);
        if (a != null)
            return ResponseEntity.ok(new AvengerResp(a));
        else
            return (ResponseEntity<AvengerResp>) ResponseEntity.notFound();
    }

    @PostMapping
    public ResponseEntity<AvengerResp> createAvenger(@RequestBody @Valid AvengerReq avenger) {
        Avenger avengerSaved = avengerStorage.createAvenger(avenger.toEntity());
        return ResponseEntity.created(
                URI.create("/api/v1/avengers/"+avengerSaved.getId())
        ).body(new AvengerResp(avengerSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvengerResp> updateAvenger(
            @PathVariable Long id,
            @RequestBody @Valid AvengerReq avenger
    ) {
        Avenger avengerUpdated = avengerStorage.updateAvenger(avenger.toEntity(id));
        if (avengerUpdated != null)
            return ResponseEntity.accepted().body(new AvengerResp(avengerUpdated));
        else
            return (ResponseEntity<AvengerResp>) ResponseEntity.notFound();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAvenger(@PathVariable Long id) {
        if (avengerStorage.deleteAvenger(id))
            return ResponseEntity.accepted().body("");
        else
            return (ResponseEntity<String>) ResponseEntity.notFound();
    }
}
