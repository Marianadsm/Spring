package com.generation.lojajogos.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojajogos.model.Usuario;
import com.generation.lojajogos.repository.UsuarioRepository;

//A Classe UserDetailsServiceImpl é uma implementação da Interface UserDetailsService,
//responsável por validar a existência de um usuário no sistema através do Banco de dados 
//e retornar um Objeto da Classe UserDetailsImpl, com os dados encontrados no Banco de dados.

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);

		if (usuario.isPresent())
			return new UserDetailsImpl (usuario.get());
		else
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
	}
}

