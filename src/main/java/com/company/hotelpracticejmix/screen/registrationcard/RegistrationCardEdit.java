package com.company.hotelpracticejmix.screen.registrationcard;

import com.company.hotelpracticejmix.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.hotelpracticejmix.entity.RegistrationCard;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("RegistrationCard.edit")
@UiDescriptor("registration-card-edit.xml")
@EditedEntityContainer("registrationCardDc")
public class RegistrationCardEdit extends StandardEditor<RegistrationCard> {
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private InstanceContainer<RegistrationCard> registrationCardDc;
    @Autowired
    private DataManager dataManager;

    @Subscribe
    public void onAfterClose(AfterCloseEvent event) {
        if (!currentAuthentication.getUser().getUsername().equals("admin")) {

            User user = dataManager.load(User.class).condition(PropertyCondition
                    .equal("username", currentAuthentication.getUser().getUsername())).one();
            RegistrationCard registrationCard = registrationCardDc.getItem();
            registrationCard.setUser(user);

            dataManager.save(registrationCard);
        }
    }
}