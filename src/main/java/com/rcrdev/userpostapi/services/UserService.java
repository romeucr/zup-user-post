package com.rcrdev.userpostapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rcrdev.userpostapi.dto.UserDTO;
import com.rcrdev.userpostapi.entities.User;
import com.rcrdev.userpostapi.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	// used to transform and DTO into Entity, avoiding to have to do entity.setXXX
	// every time. Obs: if one of the fields are null on update request, they will be saved like that!!!
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setFullName(dto.getFullName());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
		entity.setBirthDate(dto.getBirthDate());
	}
}
