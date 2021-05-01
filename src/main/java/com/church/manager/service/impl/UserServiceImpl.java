package com.church.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.church.manager.exception.PasswordInvalid;
import com.church.manager.model.User;
import com.church.manager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<User> user = this.userRepository.findByLogin(login);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		String roles[] = user.get().isAdmin() ? new String[] { "USER", "ADMIN" } : new String[] { "USER" };

		return org.springframework.security.core.userdetails.User.builder().username(user.get().getLogin())
				.password(passwordEncoder.encode(user.get().getPassword())).roles(roles).build();

	}

	public User authenticate(User user) throws PasswordInvalid {
		Optional<User> u = this.userRepository.findByLogin(user.getLogin());
		if (passwordEncoder.matches(user.getPassword(), u.get().getPassword())) {
			return u.get();
		}
		throw new PasswordInvalid("Senha inválida !!! ");

	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public User update(User user) {
		return this.userRepository.save(user);
	}

	public Optional<User> getUserByLogin(String login) {
		return this.userRepository.findByLogin(login.trim());
	}

	public Optional<User> userValid(String login) {

		List<User> users = (List<User>) this.userRepository.findAll();

		return users.stream()
				.filter(u -> u.getLogin().equalsIgnoreCase(login.trim()))
				.findFirst();

	}
}
