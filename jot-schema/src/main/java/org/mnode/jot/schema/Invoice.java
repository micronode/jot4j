package org.mnode.jot.schema;

import java.time.ZonedDateTime;

/**
 * Used to track a schedule of financial transactions.
 */
public interface Invoice extends Jot<Invoice> {

    enum PaymentStatus { PAID, UNPAID }

    Invoice paymentStatus(PaymentStatus paymentStatus);

    Invoice paymentDueDate(ZonedDateTime paymentDueDate);

    Invoice attachment(Attachment attachment);

    interface Provider {

        Invoice invoice(String uid);
    }
}
