package com.soft.sec.demo.controller;

import com.soft.sec.demo.controller.presenter.UsuarioPresenter;
import com.soft.sec.demo.model.Usuario;
import com.soft.sec.demo.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UsuarioController {
	UsuarioRepository usuarioRepository;

	@GetMapping("/login")
	public ResponseEntity<Boolean> login(@RequestParam(name = "login") String login,
	                                     @RequestParam(name = "senha") String senha) {
		var usuario = this.usuarioRepository.findByLogin(login);
		if (usuario.isEmpty()) return ResponseEntity.ok(Boolean.FALSE);
		boolean senhaIgual = new BCryptPasswordEncoder().matches(senha, usuario.get().getSenha());

		return ResponseEntity.ok(senhaIgual);
	}

	@GetMapping("/signup")
	public ResponseEntity<UsuarioPresenter> signup(@RequestParam(name = "login") String login,
	                                     @RequestParam(name = "senha") String senha) {
		var senhaCriptografada = new BCryptPasswordEncoder().encode(senha);
		var user = new Usuario(login, senhaCriptografada);
		var usuarioCriado = this.usuarioRepository.save(user);
		return ResponseEntity.ok(new UsuarioPresenter(usuarioCriado));
	}
}
