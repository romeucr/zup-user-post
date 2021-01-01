package com.rcrdev.userpostapi.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.rcrdev.userpostapi.entities.User;
import com.rcrdev.userpostapi.services.validation.UserInsertValid;

@UserInsertValid
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "O campo nome não pode ser vazio ou nulo")
	private String fullName;
	
	@Email(message = "O formato do email informado é inválido")
	private String email;
	
	@CPF
	private String cpf;
	
	@PastOrPresent(message = "A data informada é inválida")
	private Instant birthDate;	
	
	public UserDTO() {
	}

	public UserDTO(Long id, String fullName, String email, String cpf, Instant birthDate) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.cpf = cpf;
		this.birthDate = birthDate;
	}
	
	// para converter um User em UserDTO. Como o atributo recebidos nao tem o mesmo nome dos atributos da classe, nao precisa colocar o this.atributo. 
	public UserDTO(User entity) {
		id = entity.getId();
		fullName = entity.getFullName();
		email = entity.getEmail();
		cpf = entity.getCpf();
		birthDate = entity.getBirthDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
