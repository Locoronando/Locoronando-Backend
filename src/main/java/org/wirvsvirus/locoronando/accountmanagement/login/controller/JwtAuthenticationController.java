package org.wirvsvirus.locoronando.accountmanagement.login.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wirvsvirus.locoronando.accountmanagement.db.UserRepository;
import org.wirvsvirus.locoronando.accountmanagement.login.model.TokenRequest;
import org.wirvsvirus.locoronando.accountmanagement.model.User;
import org.wirvsvirus.locoronando.customer.CustomerRepository;
import org.wirvsvirus.locoronando.dealer.DealerRepository;
import org.wirvsvirus.locoronando.request.Participant;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequestMapping
public class JwtAuthenticationController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity createUser(@RequestBody User request) {
    User userPerUsername = userRepository.findByUsername(request.getUsername());
    User userPerEmail = userRepository.findByEmail(request.getEmail());

    if (userPerEmail == null && userPerUsername == null) {
      userRepository.save(request);
    } else if (userPerEmail == null) {
      return new ResponseEntity("This username already exists!", HttpStatus.CONFLICT);
    } else if (userPerUsername == null) {
      return new ResponseEntity("This email is already taken!", HttpStatus.CONFLICT);
    } else {
      return new ResponseEntity("This username already exists!", HttpStatus.CONFLICT);
    }

    return this.getAuthorizationResponse(request);
  }

  @GetMapping("/login")
  public ResponseEntity<User> login(@RequestBody TokenRequest request) {
    User user = userRepository.findByUsername(request.getUsername());

    if (user == null)
      return new ResponseEntity("Invalid credentials! Please enter the correct username and password", HttpStatus.CONFLICT);

    if(!request.getPassword().equals(user.getPassword()))
      return new ResponseEntity("Invalid credentials! Please enter the correct username and password", HttpStatus.CONFLICT);

    return this.getAuthorizationResponse(user);
  }

  private ResponseEntity<User> getAuthorizationResponse(User request) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", generateTokenFromUser(request));
    return new ResponseEntity("Successfully logged in", headers, HttpStatus.CREATED);
  }

  private String generateTokenFromUser(User user) {
    try {
      return Jwts.builder()
        .setExpiration(new Date(1300819380))
        .claim("username", user.getUsername())
        .claim("email", user.getEmail())
        .claim("id", user.getId())
        .signWith(
          SignatureAlgorithm.HS256,
          "secret".getBytes("UTF-8")
        )
        .compact();
    } catch (Exception ignored) {
      return "";
    }
  }


  private void authenticate(String username, String password) throws Exception {
    //Here goes the authentication of the requesting user
  }
}