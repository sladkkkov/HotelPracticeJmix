package com.company.hotelpracticejmix.security;

import com.company.hotelpracticejmix.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "SimpleUser", code = "simple-user")
public interface SimpleUserRole {

    @SpecificPolicy(resources = "*")
    void specific();

    @MenuPolicy(menuIds = "*")
    @ScreenPolicy(screenIds = {"Apartaments.browse", "HomeUserView", "RegistrationCard.edit", "RegistrationCard.browse", "themeSettingsScreen", "User.browse", "ui_JmxConsoleScreen", "entityInspector.browse", "sec_ResourceRoleModel.browse", "sec_RowLevelRoleModel.browse", "Apartaments.edit", "User.edit", "LoginScreen", "MainScreen", "RegistrationCardCovid.edit", "RegistrationCardFragment", "ui_UiDataFilterConfigurationModel.fragment", "selectValueDialog", "notFoundScreen", "ui_MBeanInspectScreen", "ui_MBeanAttribute.edit", "ui_MBeanOperationFragment", "ui_MBeanOperationResultScreen", "ui_FilterConfigurationModel.fragment", "inputDialog", "ui_PropertyFilterCondition.edit", "ui_JpqlFilterCondition.edit", "ui_GroupFilterCondition.edit", "ui_AddConditionScreen", "entityInfoWindow", "entityInspector.edit", "ChangePasswordDialog", "ResetPasswordDialog", "sec_EntityAttributeResourcePolicyModel.create", "sec_EntityAttributeResourcePolicyModel.edit", "sec_EntityResourcePolicyModel.create", "sec_EntityResourcePolicyModel.edit", "sec_GraphQLResourcePolicyModel.edit", "sec_MenuResourcePolicyModel.create", "sec_MenuResourcePolicyModel.edit", "sec_ResourcePolicyModel.edit", "sec_ScreenResourcePolicyModel.create", "sec_ScreenResourcePolicyModel.edit", "sec_SpecificResourcePolicyModel.edit", "sec_ResourceRoleModel.edit", "sec_ResourceRoleModel.lookup", "sec_RoleAssignmentScreen", "sec_RoleAssignmentFragment", "sec_RowLevelPolicyModel.edit", "sec_RowLevelRoleModel.edit", "sec_RowLevelRoleModel.lookup", "sec_UserSubstitutionEntity.edit", "sec_UserSubstitutionsFragment", "sec_UserSubstitutionsScreen", "backgroundWorkProgressScreen", "bulkEditorWindow", "ui_LayoutAnalyzerScreen", "singleFileUploadDialog"})
    void screens();

    @EntityAttributePolicy(entityClass = Contact.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Contact.class, actions = EntityPolicyAction.ALL)
    void contact();

    @EntityAttributePolicy(entityClass = RegistrationCard.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = RegistrationCard.class, actions = EntityPolicyAction.ALL)
    void registrationCard();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Apartaments.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Apartaments.class, actions = EntityPolicyAction.ALL)
    void apartaments();

    @EntityAttributePolicy(entityClass = CalendarEvent.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = CalendarEvent.class, actions = EntityPolicyAction.ALL)
    void calendarEvent();
}