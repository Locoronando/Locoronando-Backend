package org.wirvsvirus.locoronando.dealer;

import org.springframework.data.repository.CrudRepository;
import org.wirvsvirus.locoronando.dealer.model.db.Dealer;

public interface DealerRepository extends CrudRepository<Dealer, Long>, GeoQueryExtension<Dealer> {
}
