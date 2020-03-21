package org.wirvsvirus.locoronando.request;

import java.util.UUID;

public final class User {

  private final Participant participant;
  private final long id;
  private final String name;

  public User(Participant participant, long id) {
    this.participant = participant;
    this.id = id;

    this.name = UUID.randomUUID().toString();
  }

  public Participant getParticipant() {
    return participant;
  }

  public long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != user.id) return false;
    return participant == user.participant;
  }

  @Override
  public int hashCode() {
    int result = participant != null ? participant.hashCode() : 0;
    result = 31 * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
      "participant=" + participant +
      ", id=" + id +
      '}';
  }
}
