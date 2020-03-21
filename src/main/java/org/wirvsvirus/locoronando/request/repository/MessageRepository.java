package org.wirvsvirus.locoronando.request.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wirvsvirus.locoronando.request.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
