package com.gfg.showtime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.gfg.showtime.notification.BookingNotification;
import com.gfg.showtime.notification.NotificationService;

// A plain unit test with Mockito mocks (fakes): no Spring context, no mail server
class NotificationServiceTest {

    final BookingNotification booking = new BookingNotification(7, "Asha", "asha@example.com", "98765",
            "Inception", "PVR Phoenix", LocalDateTime.of(2030, 1, 1, 18, 0), "2A 2B", 600);

    @Test
    void emailGoesToTheCustomerWithTheSeats() {
        JavaMailSender sender = mock(JavaMailSender.class);

        service(sender).send(booking);

        ArgumentCaptor<SimpleMailMessage> mail = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(sender).send(mail.capture());
        assertThat(mail.getValue().getTo()).containsExactly("asha@example.com");
        assertThat(mail.getValue().getText()).contains("2A 2B", "PVR Phoenix");
    }

    @Test
    void noMailServerOrAFailingOneIsNotAnError() {
        service(null).send(booking);                          // no JavaMailSender bean: the email is only logged

        JavaMailSender broken = mock(JavaMailSender.class);
        doThrow(new MailSendException("SMTP down")).when(broken).send(any(SimpleMailMessage.class));
        service(broken).send(booking);                        // the error is logged, not thrown
    }

    @SuppressWarnings("unchecked")
    private static NotificationService service(JavaMailSender sender) {
        ObjectProvider<JavaMailSender> provider = mock(ObjectProvider.class);
        when(provider.getIfAvailable()).thenReturn(sender);
        return new NotificationService(provider);
    }
}
