package com.company.hotelpracticejmix.screen.registrationcard;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import io.jmix.ui.screen.*;

@UiController("RegistrationCard.edit")
@UiDescriptor("registration-card-edit.xml")
@EditedEntityContainer("registrationCardDc")
public class RegistrationCardEdit extends StandardEditor<RegistrationCard> {
}