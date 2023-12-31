package com.ongmap.controllers;

import com.ongmap.models.ong.OngDetails;
import com.ongmap.models.parceiro.ParceiroDetails;
import com.ongmap.models.parceiro.ParceirosRequest;
import com.ongmap.services.ParceirosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/parceiros")

public class ParceirosController {
    @Autowired
    private ParceirosService parceirosService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ParceirosRequest parceiros, UriComponentsBuilder uriBuilder){
        var parceirosAux = parceiros.toParceiros();
        var aux = parceirosService.create(parceirosAux);
        var uri = uriBuilder.path("/parceiros/{cnpj}").buildAndExpand(aux.getCnpj()).toUri();
        return ResponseEntity.created(uri).body(new ParceiroDetails(aux));
    }
    @GetMapping("/{cnpj}")
    public ResponseEntity get(@PathVariable String cnpj) {
        var parceiro = parceirosService.getBycnpj(cnpj);
        return ResponseEntity.ok(new ParceiroDetails(parceiro));
    }
    @DeleteMapping("/{cnpj}")
    public ResponseEntity delete(@PathVariable String cnpj) {
        parceirosService.delete(cnpj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity findAll(@PageableDefault Pageable page) {
        var parceiros = parceirosService.findAll(page);
        return ResponseEntity.ok(parceiros.stream().map(ParceiroDetails::new));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ParceirosRequest parceirosRequest){
        return ResponseEntity.ok(parceirosService.update(parceirosRequest.toParceiros()));
    }


}
