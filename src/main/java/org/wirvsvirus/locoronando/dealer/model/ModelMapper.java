package org.wirvsvirus.locoronando.dealer.model;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;
import org.wirvsvirus.locoronando.dealer.model.rest.DealerUpdate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapper {
  public List<DealerUpdate> map(Iterable<Dealer> dbDealers) {
    List<DealerUpdate> dealerUpdates = new ArrayList<>();

    for (Dealer dbDealer : dbDealers) {
     final  DealerUpdate dealerUpdate = map(dbDealer);
      dealerUpdates.add(dealerUpdate);
    }

    return dealerUpdates;
  }

  public DealerUpdate map(Dealer dbDealer) {
    final DealerUpdate dealerUpdate = new DealerUpdate();

    BeanUtils.copyProperties(dbDealer, dealerUpdate);
    return dealerUpdate;
  }

  public Dealer map(DealerUpdate restDealerUpdate) {
    final Dealer dealer = new Dealer();
    BeanUtils.copyProperties(restDealerUpdate, dealer);
    return dealer;
  }
}
