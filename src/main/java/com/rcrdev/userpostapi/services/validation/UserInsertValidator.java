package com.rcrdev.userpostapi.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rcrdev.userpostapi.dto.UserDTO;
import com.rcrdev.userpostapi.entities.User;
import com.rcrdev.userpostapi.repositories.UserRepository;
import com.rcrdev.userpostapi.resources.exceptions.FieldMessage;


// também boiler plate code
// interface generics, parametrizando o tipo da annotation e o tipo da classe que vai receber o annotation 
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserDTO> {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}
	
	// isValid é um método do ConstraintValidator que testa se o objeto UserDTO é válido ou não, baseado na lógica abaixo
	@Override
	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		User user = repository.findByEmail(dto.getEmail());
		if (user != null) {
			list.add(new FieldMessage("email", "email já cadastrado"));
		}

		user = repository.findByCpf(dto.getCpf());
		if (user != null) {
			list.add(new FieldMessage("cpf", "cpf já cadastrado"));
		}
		
		//insere os items da lista de erro list no lista de erros do Beans Validation
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
