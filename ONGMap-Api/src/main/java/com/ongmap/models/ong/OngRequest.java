package com.ongmap.models.ong;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record OngRequest(@Pattern(regexp = "[0-9]{14}", message = "CNPJ inválido")
                         String cnpj,
                         @NotBlank(message = "Nome não pode ser vazio")
                         String nome,
                         @NotBlank(message = "Telefone não pode ser vazio")
                         String telefone,
                         @NotNull(message = "Endereço não pode ser vazio")
                         Endereco endereco,
                         String missao,
                         @NotNull(message = "Área de atuação não pode ser vazia")
                         AreaAtuacao areaAtuacao,
                         boolean ativo) {
    public Ong toOng() {
        return new Ong(cnpj, nome, telefone, endereco, missao, areaAtuacao, ativo);
    }
}
