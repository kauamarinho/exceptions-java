package com.kauam.domain.exception;

import java.math.BigDecimal;

public class InvalidSalaryException extends BusinessException {

    public InvalidSalaryException(BigDecimal salary) {
        super("Salário deve ser maior que zero. Recebido: " + salary);
    }
}
