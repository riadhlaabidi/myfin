package tn.riadh.myfin.core.terminal.domain;

import java.util.UUID;

public final class TerminalId {

    private final UUID value;

    private TerminalId(final UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("TerminalId cannot be null");
        }
        this.value = value;
    }

    public static TerminalId generate() {
        return new TerminalId(UUID.randomUUID());
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TerminalId)) {
            return false;
        }
        TerminalId other = (TerminalId) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
