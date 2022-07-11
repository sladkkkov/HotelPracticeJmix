package com.company.hotelpracticejmix.screen.registrationcard;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import com.company.hotelpracticejmix.service.UserService;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("RegistrationCard.edit")
@UiDescriptor("registration-card-edit.xml")
@EditedEntityContainer("registrationCardDc")
public class RegistrationCardEdit extends StandardEditor<RegistrationCard> {
    @Autowired
    private InstanceContainer<RegistrationCard> registrationCardDc;
    @Autowired
    private UserService userService;
    /**
     * Метод устанавливает User
     */
    public void getUserRegistrationCardOnScreen() {
        userService.setUserByRegistrationCardIfNotAdmin(registrationCardDc.getItem());
    }

    @Subscribe
    public void onAfterClose(AfterCloseEvent event) {
        getUserRegistrationCardOnScreen();
    }
}