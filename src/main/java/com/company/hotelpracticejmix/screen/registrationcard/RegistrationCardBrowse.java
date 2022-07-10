package com.company.hotelpracticejmix.screen.registrationcard;

import com.company.hotelpracticejmix.app.CovidImageFormatter;
import com.company.hotelpracticejmix.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.company.hotelpracticejmix.entity.RegistrationCard;
import org.aspectj.apache.bcel.generic.TABLESWITCH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@UiController("RegistrationCard.browse")
@UiDescriptor("registration-card-browse.xml")
@LookupComponent("registrationCardsTable")
public class RegistrationCardBrowse extends StandardLookup<RegistrationCard> {

}