package org.wirvsvirus.locoronando.dealer;

import org.springframework.data.repository.CrudRepository;

public interface DealerRepository extends CrudRepository<Dealer, Integer> {

  Dealer findByOwnerName(String username);

  Dealer findByEmail(String email);

  boolean existsDealerByShopName(String shopName);
}
