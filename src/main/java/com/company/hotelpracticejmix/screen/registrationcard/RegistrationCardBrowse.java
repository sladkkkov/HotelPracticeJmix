package com.company.hotelpracticejmix.screen.registrationcard;

import com.company.hotelpracticejmix.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.screen.*;
import com.company.hotelpracticejmix.entity.RegistrationCard;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("RegistrationCard.browse")
@UiDescriptor("registration-card-browse.xml")
@LookupComponent("registrationCardsTable")
public class RegistrationCardBrowse extends StandardLookup<RegistrationCard> {

}