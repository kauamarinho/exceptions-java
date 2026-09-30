package com.kauam.domain.service;

import com.kauam.domain.exception.DuplicateCpfException;
import com.kauam.domain.exception.EmployeeNotFoundException;
import com.kauam.domain.exception.InvalidCpfException;
import com.kauam.domain.model.Employee;
import com.kauam.domain.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * O SERVICE é quem LANÇA (throw) as exceções de negócio.
 * Repare: aqui não existe NENHUM try/catch. O service não sabe
 * se está rodando num console, numa API REST ou num teste —
 * então ele não decide COMO mostrar o erro, só AVISA que ele aconteceu.
 */
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ============================================================
    //  Comandos
    // ============================================================

    public void register(Employee employee) {
        // "Guard clauses": valida e lança logo no início.
        // O caminho feliz fica sem aninhamento, no final do método.
        if (!isValidCpf(employee.getCpf())) {
            throw new InvalidCpfException(employee.getCpf());
        }
        if (employeeRepository.existsByCpf(employee.getCpf())) {
            throw new DuplicateCpfException(employee.getCpf());
        }
        employeeRepository.save(employee);
    }

    public void remove(String cpf) {
        if (!employeeRepository.deleteByCpf(cpf)) {
            throw new EmployeeNotFoundException(cpf);
        }
    }

    public void giveRaise(String cpf, BigDecimal percentage) {
        // findByCpf pode lançar EmployeeNotFoundException;
        // applyRaise pode lançar IllegalArgumentException.
        // Nenhuma das duas é tratada aqui: elas SOBEM para quem chamou.
        findByCpf(cpf).applyRaise(percentage);
    }

    // ============================================================
    //  Consultas
    // ============================================================

    public Employee findByCpf(String cpf) {
        return employeeRepository.findByCpf(cpf)
                .orElseThrow(() -> new EmployeeNotFoundException(cpf));
    }

    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    public BigDecimal monthlyPayroll() {
        return employeeRepository.findAll().stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // ============================================================
    //  Validação de CPF (mesmo algoritmo do employee-management-system)
    // ============================================================

    private boolean isValidCpf(String cpf) {
        if (!cpf.matches("\\d{11}") || cpf.chars().distinct().count() == 1) {
            return false;
        }
        int[] digits = cpf.chars().map(c -> c - '0').toArray();
        return digits[9] == checkDigit(digits, 9) && digits[10] == checkDigit(digits, 10);
    }

    private int checkDigit(int[] digits, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += digits[i] * (length + 1 - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
