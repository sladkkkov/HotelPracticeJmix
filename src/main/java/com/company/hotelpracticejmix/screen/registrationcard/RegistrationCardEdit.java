package com.company.hotelpracticejmix.screen.registrationcard;

import com.company.hotelpracticejmix.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.ResourceRole;
import io.jmix.security.role.assignment.RoleAssignment;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.hotelpracticejmix.entity.RegistrationCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

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

    public void getUserRegistrationCardOnScreen(){
        if (getRoleNames(currentAuthentication.getAuthentication()).equals("system-full-access")) {
            User user = dataManager
                    .load(User.class)
                    .condition(PropertyCondition
                    .equal("username", currentAuthentication.getUser().getUsername())).one();
            RegistrationCard registrationCard = registrationCardDc.getItem();
            registrationCard.setUser(user);

            dataManager.save(registrationCard);
        }
    }

    private String getRoleNames(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
    @Subscribe
    public void onAfterClose(AfterCloseEvent event) {
        getUserRegistrationCardOnScreen();
    }
}