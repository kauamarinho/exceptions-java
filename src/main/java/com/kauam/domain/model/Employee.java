package com.kauam.domain.model;

import com.kauam.domain.exception.InvalidSalaryException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Employee {
    private final String name;
    private final String cpf;
    private BigDecimal salary;

    public Employee(String name, String cpf, BigDecimal salary) {
        // Validar no construtor = "fail fast": o objeto nunca nasce inválido.
        // Objects.requireNonNull lança NullPointerException com mensagem clara.
        this.name = Objects.requireNonNull(name, "name não pode ser nulo");
        this.cpf = Objects.requireNonNull(cpf, "cpf não pode ser nulo");
        this.salary = validateSalary(salary);
    }

    public void applyRaise(BigDecimal percentage) {
        // IllegalArgumentException é uma exceção PRONTA do Java para
        // "argumento inválido". Não precisa criar classe própria para tudo.
        if (percentage.signum() < 0) {
            throw new IllegalArgumentException("Percentual de aumento não pode ser negativo: " + percentage);
        }
        BigDecimal factor = BigDecimal.ONE.add(percentage.movePointLeft(2));
        this.salary = salary.multiply(factor).setScale(2, RoundingMode.HALF_EVEN);
    }

    private static BigDecimal validateSalary(BigDecimal salary) {
        if (salary == null || salary.signum() <= 0) {
            throw new InvalidSalaryException(salary);
        }
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
