package com.ongmap.models.doadores;

public record DoadoresDetails(String cpf,
                              String nome,
                              String contato,
                              Endereco endereco) {
    public DoadoresDetails(Doadores doadores){
        this(doadores.getCpf(),
                doadores.getNome(),
                doadores.getContato(),
                doadores.getEndereco());
    }
}
