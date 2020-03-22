package org.wirvsvirus.locoronando.request;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

  List<Message> findAllByCustomerIdAndDealerId(long customerId, long dealerId);

  /**
   * Gets the messages from a specific customer
   * @param customerId the id it is sorted by
   * @return a list of messages where the customerId is the same
   */
  List<Message> findAllByCustomerId(long customerId);

  /**
   * Gets the messages from a specific dealer
   * @param dealerId the id it is sorted by
   * @return a list of messages where the dealerId is the same
   */
  List<Message> findAllByDealerId(long dealerId);
}
