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
import org.wirvsvirus.locoronando.customer.model.Customer;
import org.wirvsvirus.locoronando.dealer.Dealer;
import org.wirvsvirus.locoronando.dealer.DealerRepository;
import org.wirvsvirus.locoronando.request.Participant;

import java.util.Date;

@RestController
@RequestMapping
public class JwtAuthenticationController {

  @Autowired
  private DealerRepository dealerRepository;

  @Autowired
  private CustomerRepository customerRepository;

  /**
   * This method implements the path of registering a dealer in the system. It will give back
   * the Authentication Header if the registering was successful
   *
   * @param request The Dealer given by the frontend
   * @return the Response Entity including the Authentication Header
   */
  @PostMapping("/register/dealer")
  public ResponseEntity createDealer(@RequestBody Dealer request) {
    Dealer userPerUsername = dealerRepository.findByOwnerName(request.getOwnerName());
    Dealer userPerEmail = dealerRepository.findByEmail(request.getEmail());

    if(dealerRepository.existsDealerByShopName(request.getShopName()))
      return new ResponseEntity("This shop name already exists!", HttpStatus.CONFLICT);

    if (userPerEmail == null && userPerUsername == null) {
      dealerRepository.save(request);
    } else if (userPerEmail == null) {
      return new ResponseEntity("This username already exists!", HttpStatus.CONFLICT);
    } else if (userPerUsername == null) {
      return new ResponseEntity("This email is already taken!", HttpStatus.CONFLICT);
    } else {
      return new ResponseEntity("This username already exists!", HttpStatus.CONFLICT);
    }

    return this.getAuthorizationResponseForDealer(request);
  }

  /**
   * This method implements the path of registering a customer in the system. It will give back
   * the Authentication Header if the registering was successful
   *
   * @param request The Customer given by the frontend
   * @return the Response Entity including the Authentication Header
   */
  @PostMapping("/register/customer")
  public ResponseEntity createCustomer(@RequestBody Customer request) {
    Customer userPerUsername = customerRepository.findByName(request.getName());
    Customer userPerEmail = customerRepository.findByEmail(request.getEmail());

    if (userPerEmail == null && userPerUsername == null) {
      customerRepository.save(request);
    } else if (userPerEmail == null) {
      return new ResponseEntity("This username already exists!", HttpStatus.CONFLICT);
    } else if (userPerUsername == null) {
      return new ResponseEntity("This email is already taken!", HttpStatus.CONFLICT);
    } else {
      return new ResponseEntity("This username already exists!", HttpStatus.CONFLICT);
    }

    return this.getAuthorizationResponseForCustomer(request);
  }

  @GetMapping("/login")
  public ResponseEntity login(@RequestBody TokenRequest request) {
    if(request.getType().equals(Participant.DEALER)) return loginDealer(request);
    return loginCustomer(request);
  }

  private ResponseEntity loginDealer(TokenRequest request) {
    Dealer dealer = dealerRepository.findByOwnerName(request.getUsername());
    if (dealer == null)
      return new ResponseEntity("Invalid credentials! Please enter the correct username and password", HttpStatus.CONFLICT);

    if(!request.getPassword().equals(dealer.getPassword()))
      return new ResponseEntity("Invalid credentials! Please enter the correct username and password", HttpStatus.CONFLICT);

    return this.getAuthorizationResponseForDealer(dealer);
  }

  private ResponseEntity loginCustomer(TokenRequest request) {
    Customer customer = customerRepository.findByName(request.getUsername());
    if (customer == null)
      return new ResponseEntity("Invalid credentials! Please enter the correct username and password", HttpStatus.CONFLICT);

    if(!request.getPassword().equals(customer.getPassword()))
      return new ResponseEntity("Invalid credentials! Please enter the correct username and password", HttpStatus.CONFLICT);

    return this.getAuthorizationResponseForCustomer(customer);
  }

  private ResponseEntity<User> getAuthorizationResponseForCustomer(Customer request) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", generateTokenFromCustomer(request));
    return new ResponseEntity("Successfully logged in", headers, HttpStatus.CREATED);
  }

  private ResponseEntity<User> getAuthorizationResponseForDealer(Dealer request) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", generateTokenFromDealer(request));
    return new ResponseEntity("Successfully logged in", headers, HttpStatus.CREATED);
  }

  private String generateTokenFromCustomer(Customer customer) {
    try {
      return Jwts.builder()
        .setExpiration(new Date(1300819380))
        .claim("username", customer.getName())
        .claim("email", customer.getEmail())
        .claim("id", customer.getId())
        .signWith(
          SignatureAlgorithm.HS256,
          "secret".getBytes("UTF-8")
        )
        .compact();
    } catch (Exception ignored) {
      return "";
    }
  }

  private String generateTokenFromDealer(Dealer dealer) {
    try {
      return Jwts.builder()
        .setExpiration(new Date(1300819380))
        .claim("shopName", dealer.getShopName())
        .claim("username", dealer.getOwnerName())
        .claim("email", dealer.getEmail())
        .claim("id", dealer.id())
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