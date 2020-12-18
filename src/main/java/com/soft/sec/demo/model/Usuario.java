package com.soft.sec.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue
	Long id;

	@NonNull
	String login;

	@NonNull
	String senha;
}
