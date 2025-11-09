//AuthenticationService.java
package com.marmitech.Marmitech.auth;

import com.marmitech.Marmitech.Config.JwtServiceGenerator;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.marmitech.Marmitech.Entity.Usuario;


@Service
public class LoginService {

	@Autowired
	// Alterado para usar usuarioRepository em vez de loginRepository
	//private LoginRepository repository;
   private UsuarioRepository usuarioRepository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;



	public String logar(Login login) {

		String token = this.gerarToken(login);
		return token;

	}



	public String gerarToken(Login login) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getUsername(),
						login.getPassword()
						)
				);
		Usuario user = usuarioRepository.findByNome(login.getUsername()).get();
		String jwtToken = jwtService.generateToken(user);
		return jwtToken;


//		Usuario user = usuarioRepository
//				.findByNomeAndSenha(login.getNome(), login.getSenha())
//				.orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));
//
//		return jwtService.generateToken(user);
	}


}
