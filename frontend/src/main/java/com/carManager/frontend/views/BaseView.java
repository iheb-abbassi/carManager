package com.carManager.frontend.views;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class BaseView extends VerticalLayout {

    private static final int SUCCESS_DURATION_MS = 3000;
    private static final int ERROR_DURATION_MS = 5000;

    protected void showSuccess(String message) {
        Notification.show(message, SUCCESS_DURATION_MS, Notification.Position.BOTTOM_START)
            .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    protected void showError(String message) {
        Notification.show(message, ERROR_DURATION_MS, Notification.Position.BOTTOM_START)
            .addThemeVariants(NotificationVariant.LUMO_ERROR);
    }
}
