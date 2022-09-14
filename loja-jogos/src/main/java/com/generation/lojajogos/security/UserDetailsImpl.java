package com.generation.lojajogos.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.lojajogos.model.Usuario;

//A Classe UserDetailsImpl implementa a Interface UserDetails, que tem como principal funcionalidade fornecer as 
//informações básicas do usuário para o Spring Security (Usuário, Senha, Direitos de acesso e as Restrições da conta).

public class UserDetailsImpl implements UserDetails {
	
private static final long serialVersionUID =1L;
	
	private String userName;
	private String password;
	
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl (Usuario user){
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl (){ }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return authorities;
	}

	@Override
	public String getPassword() {
	
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}
//Indica se a conta do usuário expirou. Uma conta expirada não pode ser autenticada 
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
//Indica se o usuário está bloqueado ou desbloqueado. Um usuário bloqueado não pode ser autenticado 
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
//Indica se as credenciais do usuário (senha) expiraram. Senha expirada impede a autenticação 
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
//Indica se o usuário está habilitado ou desabilitado. Um usuário desabilitado não pode ser autenticado
    @Override
	public boolean isEnabled() {
		
		return true;
	}

}

