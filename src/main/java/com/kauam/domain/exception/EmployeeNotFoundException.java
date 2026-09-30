package com.kauam.domain.exception;

public class EmployeeNotFoundException extends BusinessException {

    public EmployeeNotFoundException(String cpf) {
        super("Funcionário com CPF " + cpf + " não encontrado.");
    }
}
