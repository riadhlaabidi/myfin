package tn.riadh.myfin.shared.domain;

import java.time.Instant;
import java.util.UUID;

import org.jmolecules.event.types.DomainEvent;

public abstract class AbstractDomainEvent implements DomainEvent {
    private final UUID eventId;
    private final Instant occurredAt;

    protected AbstractDomainEvent(UUID eventId, Instant occurredAt) {
        this.eventId = eventId;
        this.occurredAt = occurredAt;
    }

    public UUID eventId() {
        return eventId;
    }

    public Instant occurredAt() {
        return occurredAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractDomainEvent)) {
            return false;
        }
        AbstractDomainEvent other = (AbstractDomainEvent) obj;
        return eventId.equals(other.eventId);
    }

    @Override
    public int hashCode() {
        return eventId.hashCode();
    }
}
