package com.jwt.controller;

import com.jwt.util.JwtUtil;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserService customUserService;

	@Autowired
	private JwtUtil jwtUtilHelper;

	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		System.out.println(jwtRequest);

		try{

         this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));

		}catch (Exception e){
            e.printStackTrace();

			throw  new Exception(" Bad Credentials");

		}


		UserDetails userDetails= this.customUserService.loadUserByUsername(jwtRequest.getUsername());

	      String token=this.jwtUtilHelper.generateToken(userDetails);
		System.out.println(" JWT "+token);

		return  ResponseEntity.ok(new JwtResponse(token));
	}
}
