package com.kauam.infrastructure;

import com.kauam.domain.exception.PayrollExportException;
import com.kauam.domain.model.Employee;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Infraestrutura = código que conversa com o "mundo de fora" (arquivo, banco, rede).
 * É aqui que aparecem as exceções CHECKED do Java, como IOException.
 */
public class PayrollFileExporter {

    //                                         vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //  "throws" na ASSINATURA = aviso/contrato: "este método PODE lançar isso,
    //  e quem me chamar precisa lidar com isso".
    public void export(List<Employee> employees, Path file) throws PayrollExportException {

        // try-with-resources: tudo que for declarado dentro de try( ... )
        // é FECHADO automaticamente no fim, dando erro ou não.
        // Substitui o antigo "finally { writer.close(); }".
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write("nome;cpf;salario");
            writer.newLine();
            for (Employee employee : employees) {
                writer.write(employee.getName() + ";" + employee.getCpf() + ";" + employee.getSalary());
                writer.newLine();
            }
        } catch (IOException e) {
            // TRADUZ a exceção técnica (IOException) para uma exceção do domínio,
            // passando "e" como CAUSA para não perder o stack trace original.
            throw new PayrollExportException("Não foi possível exportar a folha para " + file, e);
        }
    }
}
