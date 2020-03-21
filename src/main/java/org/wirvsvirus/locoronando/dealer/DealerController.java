package org.wirvsvirus.locoronando.dealer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("dealer")
@RequiredArgsConstructor
@Slf4j
public class DealerController {
  private final DealerRepository dealerRepository;

  @PostMapping
  @ResponseStatus(NO_CONTENT)
  public void create(@RequestBody Dealer dealer) {
    dealerRepository.save(dealer);
  }

  @GetMapping(path = "find")
  public Iterable<Dealer> find() {
    return dealerRepository.findAll();
  }

}
