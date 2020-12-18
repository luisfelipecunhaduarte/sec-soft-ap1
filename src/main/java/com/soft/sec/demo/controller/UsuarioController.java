package com.soft.sec.demo.controller;

import com.soft.sec.demo.controller.presenter.UsuarioPresenter;
import com.soft.sec.demo.model.Usuario;
import com.soft.sec.demo.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UsuarioController {
	UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody Usuario usuario) {
		var user = this.usuarioRepository.findByLogin(usuario.getLogin());
		if (user.isEmpty()) return ResponseEntity.ok(Boolean.FALSE);
		boolean senhaIgual = new BCryptPasswordEncoder().matches(usuario.getSenha(), user.get().getSenha());

		return ResponseEntity.ok(senhaIgual);
	}

	@PostMapping("/signup")
	public ResponseEntity<UsuarioPresenter> signup(@RequestBody Usuario usuario) {
		var senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		var user = new Usuario(usuario.getLogin(), senhaCriptografada);
		var usuarioCriado = this.usuarioRepository.save(user);
		return ResponseEntity.ok(new UsuarioPresenter(usuarioCriado));
	}
}
