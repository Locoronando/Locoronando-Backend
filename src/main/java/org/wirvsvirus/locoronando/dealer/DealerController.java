package org.wirvsvirus.locoronando.dealer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wirvsvirus.locoronando.dealer.model.ModelMapper;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;
import org.wirvsvirus.locoronando.dealer.model.rest.DealerUpdate;

import javax.websocket.server.PathParam;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("dealer")
@RequiredArgsConstructor
@Slf4j
public class DealerController {
  private final DealerService dealerService;
  private final ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(NO_CONTENT)
  public void create(@RequestBody DealerUpdate dealerUpdate) {
    dealerService.create(modelMapper.map(dealerUpdate));
  }

  @GetMapping(path = "find/{PLZ}")
  public Iterable<Dealer> find(@PathParam("PLZ") String plz) {
    return dealerService.findByLocation(plz);
  }

}
