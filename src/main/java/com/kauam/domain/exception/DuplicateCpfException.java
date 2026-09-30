package com.kauam.domain.exception;

/**
 * Separada de InvalidCpfException de propósito:
 * "CPF mal formatado" e "CPF já cadastrado" são problemas DIFERENTES,
 * então merecem tipos diferentes (quem captura pode reagir diferente).
 */

public class DuplicateCpfException extends BusinessException {

    public DuplicateCpfException(String cpf) {
        super("CPF já cadastrado: " + cpf);
    }
}
