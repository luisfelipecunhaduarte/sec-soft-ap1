package com.soft.sec.demo.controller.presenter;

import com.soft.sec.demo.model.Usuario;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class UsuarioPresenter {
	Long id;
	String login;

	public UsuarioPresenter(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
	}
}

