package com.church.manager.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.church.manager.service.JwtService;
import com.church.manager.service.impl.UserServiceImpl;

public class JwtFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UserServiceImpl userServiceImpl;

	public JwtFilter( JwtService jwtService, UserServiceImpl userServiceImpl ) {
		this.jwtService = jwtService;
		this.userServiceImpl = userServiceImpl;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		String authorization = httpServletRequest.getHeader("Authorization");

		if( authorization != null && authorization.startsWith("Bearer")){
			String token = authorization.split(" ")[1];
			
			if(jwtService.tokenIsValid(token).isPresent()){
				String loginUsuario = this.jwtService.getLoginUser(token).get();
				UserDetails usuario = this.userServiceImpl.loadUserByUsername(loginUsuario);
				UsernamePasswordAuthenticationToken user = new
						UsernamePasswordAuthenticationToken(usuario,null,
								usuario.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
				
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);

	}
}
