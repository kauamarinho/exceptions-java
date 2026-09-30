package com.kauam.domain.exception;

/**
 * Exceção CHECKED (extends Exception, e não RuntimeException).
 *
 * Quem chamar um método que lança essa exceção é OBRIGADO pelo compilador a:
 *   a) tratar com try/catch, ou
 *   b) repassar declarando "throws PayrollExportException".
 *
 * Faz sentido ser checked porque é uma falha EXTERNA (disco, permissão, pasta)
 * que o programa pode e deve se recuperar — não é bug de código.
 *
 * O construtor recebe "cause": a exceção original (ex: IOException).
 * Isso se chama ENCADEAMENTO de exceções — a gente traduz o erro técnico
 * para um erro do nosso domínio SEM perder a informação original.
 */
public class PayrollExportException extends Exception {

    public PayrollExportException(String message, Throwable cause) {
        super(message, cause);
    }
}
