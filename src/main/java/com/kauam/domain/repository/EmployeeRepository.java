package com.kauam.domain.repository;

import com.kauam.domain.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * O repositório NÃO lança exceção de negócio: ele só responde
 * "achei / não achei" (Optional, boolean). Quem decide se "não achar"
 * é um erro é o SERVICE, que conhece a regra de negócio.
 */
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public void save(Employee employee) {
        employees.add(employee);
    }

    public Optional<Employee> findByCpf(String cpf) {
        return employees.stream()
                .filter(employee -> employee.getCpf().equals(cpf))
                .findFirst();
    }

    public boolean existsByCpf(String cpf) {
        return findByCpf(cpf).isPresent();
    }

    public boolean deleteByCpf(String cpf) {
        return employees.removeIf(employee -> employee.getCpf().equals(cpf));
    }

    public List<Employee> findAll() {
        return List.copyOf(employees);
    }
}
