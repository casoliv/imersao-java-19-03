package br.com.targettrust.traccadastros.entidades;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ClienteValidationTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void testValidationSuccess() {
        Cliente cliente = buildValidCliente();
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertThat(violations, empty());
    }

    @Test
    public void testInvalidEmail() {
        Cliente cliente = buildValidCliente();
        cliente.setEmail("teste");
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertThat(violations, hasSize(1));
        assertThat(violations.iterator().next().getPropertyPath().toString(), equalTo("email"));
    }

    private Cliente buildValidCliente() {
        Cliente cliente = new Cliente();
        cliente.setLogin("teste.login");
        cliente.setNome("Cliente de Teste");
        cliente.setSenha("querty123");
        cliente.setEmail("teste@targettrust.com.br");
        return cliente;
    }

}
