package com.company.hotelpracticejmix.security;

import com.company.hotelpracticejmix.entity.Apartaments;
import com.company.hotelpracticejmix.entity.Contact;
import com.company.hotelpracticejmix.entity.RegistrationCard;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "SimpleUser", code = "simple-user", scope = "UI")
public interface SimpleUserRole {
    @EntityAttributePolicy(entityClass = Apartaments.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Apartaments.class, actions = EntityPolicyAction.READ)
    void apartaments();

    @EntityPolicy(entityClass = RegistrationCard.class, actions = EntityPolicyAction.CREATE)
    void registrationCard();

    @EntityPolicy(entityClass = Contact.class, actions = EntityPolicyAction.CREATE)
    void contact();

    @SpecificPolicy(resources = {"ui.loginToUi", "datatools.ui.showEntityInfo"})
    void specific();

    @MenuPolicy(menuIds = {"Apartaments.browse", "RegistrationCard.browse"})
    @ScreenPolicy(screenIds = {"Apartaments.browse", "RegistrationCard.browse"})
    void screens();
}