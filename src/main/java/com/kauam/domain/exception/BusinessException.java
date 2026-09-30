package com.kauam.domain.exception;

/**
 * Classe BASE de todas as exceções de regra de negócio do sistema.
 *
 * Por que ter uma classe base?
 *  - Na camada de UI eu posso fazer UM único catch (BusinessException e)
 *    e tratar todas as regras de negócio de uma vez.
 *  - Deixa claro, só pelo tipo, o que é "erro esperado do usuário"
 *    e o que é "bug / falha inesperada".
 *
 * É UNCHECKED (extends RuntimeException): o compilador NÃO obriga
 * ninguém a declarar "throws" nem a fazer try/catch.
 */

public abstract class BusinessException extends RuntimeException {

    protected BusinessException(String message) {
        super(message);
    }
}
