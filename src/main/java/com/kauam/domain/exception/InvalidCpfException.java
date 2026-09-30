package com.kauam.domain.exception;

public class InvalidCpfException extends BusinessException {

    public InvalidCpfException(String cpf) {
        super("CPF inválido: " + cpf);
    }
}
