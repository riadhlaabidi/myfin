package tn.riadh.myfin.domain.sale;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tn.riadh.myfin.domain.common.AbstractEntity;

public class Sale extends AbstractEntity {
    private UUID publicId;
    private SaleStatus status;
    private List<SaleLine> lines;
    private List<Payment> payments;
    private Instant createdAt;

    public Sale() {
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    public List<SaleLine> getLines() {
        return lines;
    }

    public void setLines(List<SaleLine> lines) {
        this.lines = lines;
    }

    public void addSaleLine(SaleLine line) {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        lines.add(line);
    }

    public void removeSaleLine(SaleLine line) {
        if (lines == null) {
            return;
        }
        lines.removeIf(l -> l.getId() == line.getId());
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void addPayment(Payment payment) {
        if (payments == null) {
            payments = new ArrayList<>();
        }
        payments.add(payment);
    }

    public void removePayment(Payment payment) {
        if (payments == null) {
            return;
        }
        payments.removeIf(p -> p.getId() == payment.getId());
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
