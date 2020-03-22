package org.wirvsvirus.locoronando.dealer;

import java.util.Optional;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/dealer")
public final class DealerController {
  private final DealerRepository dealerRepository;

  @Autowired
  private DealerController(DealerRepository dealerRepository) {
    this.dealerRepository = dealerRepository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addDealer(@RequestBody Dealer dealer) {
    dealerRepository.save(dealer);
  }

  @GetMapping("find")
  public Dealer findDealerById(@PathParam("id") int id) {
    Optional<Dealer> optionalDealer = dealerRepository.findById(id);
    if (optionalDealer.isEmpty()) {
      return new Dealer();
    }
    return optionalDealer.get();
  }

  @GetMapping("findAll")
  public Iterable<Dealer> findAll() {
    return dealerRepository.findAll();
  }
}
