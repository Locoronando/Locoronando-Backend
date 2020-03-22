package org.wirvsvirus.locoronando.request;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public final class SocketAssignment {

  private final Map<String, User> participants;

  public SocketAssignment() {
    this.participants = new HashMap<>();
  }

  public void assign(String sessionId, User user) {
    participants.put(sessionId, user);
  }

  public Optional<User> findUserBySession(String sessionId) {
    if (!participants.containsKey(sessionId)) {
      return Optional.empty();
    } else {
      return Optional.of(participants.get(sessionId));
    }
  }

  public Optional<String> findSessionByUser(User user) {
    return participants.entrySet().stream()
      .filter(entry -> entry.getValue().equals(user))
      .map(Map.Entry::getKey)
      .findFirst();
  }
}