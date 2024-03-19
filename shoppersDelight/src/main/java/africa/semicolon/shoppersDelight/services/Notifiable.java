package africa.semicolon.shoppersDelight.services;

import africa.semicolon.shoppersDelight.exceptions.CustomerNotFoundException;
import africa.semicolon.shoppersDelight.models.Notification;

public interface Notifiable {
    String notify(Long userId, Notification notification);
}
