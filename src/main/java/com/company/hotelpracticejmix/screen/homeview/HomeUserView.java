package com.company.hotelpracticejmix.screen.homeview;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import com.company.hotelpracticejmix.entity.ResultCovid;
import com.company.hotelpracticejmix.entity.User;
import com.company.hotelpracticejmix.screen.registrationcard.RegistrationCardEdit;
import com.company.hotelpracticejmix.screen.registrationcardfragment.RegistrationCardFragment;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Fragments;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.screen.*;
import liquibase.pro.packaged.P;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("HomeUserView")
@UiDescriptor("home-user-view.xml")
public class HomeUserView extends Screen {

    @Autowired
    private Messages messages;

    @Autowired
    protected Button bookHotel;

    @Autowired
    private ScreenBuilders screenBuilders;

    @Autowired
    private Fragments fragments;

    @Autowired
    private GroupBoxLayout groupBoxLayout;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    public User getUserByCurrentAuthentication() {
        return dataManager
                .load(User.class)
                .condition(PropertyCondition
                        .equal("username", currentAuthentication.getUser().getUsername())).one();
    }

    public List<RegistrationCard> getRegistrationCardsByCurrentAuthentication() {
        return dataManager.load(RegistrationCard.class).condition(PropertyCondition.equal("user", getUserByCurrentAuthentication())).list();
    }

    public void getRegistrationCardFragments() {
        List<RegistrationCard> registrationCard = getRegistrationCardsByCurrentAuthentication();

        if (!registrationCard.isEmpty()) {
            for (RegistrationCard card : registrationCard) {
                RegistrationCardFragment registrationCardFragment = fragments.create(this, RegistrationCardFragment.class);
                registrationCardFragment.setClientName(card.getClient().getDisplayName());
                registrationCardFragment.setApartmentNumber(card.getApartaments().getDisplayName());
                registrationCardFragment.setDateDeparture(card.getDepartureDate());
                registrationCardFragment.setDateArrival(card.getArrivalDate());

                if (card.getResultsCovidTestValidation().equals(false)) {
                    registrationCardFragment.setCovidResult(false);
                    registrationCardFragment.setCovidValidationResult(messages.getMessage("localization/validationTest"));

                } else {
                    registrationCardFragment.setCovidResult(true);
                    registrationCardFragment.setCovidValidationResult(messages.getMessage("localization/acceptTest"));
                }

                registrationCardFragment.setPaymentIndication(card.getPaymentIndication());
                registrationCardFragment.setPrepaymentIndication(card.getPrepaymentIndication());
                registrationCardFragment.setUuid(card.getId());
                groupBoxLayout.add(registrationCardFragment.getFragment());
            }
        }
    }

    @Subscribe
    private void onInit(InitEvent event) {
        getRegistrationCardFragments();

        bookHotel.setAction(new BaseAction("action")
                .withHandler(actionPerformedEvent ->
                        screenBuilders.editor(RegistrationCard.class, this)
                                .withScreenClass(RegistrationCardEdit.class)
                                .build().show()));
    }
}











