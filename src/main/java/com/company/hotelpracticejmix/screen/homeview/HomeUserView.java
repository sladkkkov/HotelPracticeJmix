package com.company.hotelpracticejmix.screen.homeview;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import com.company.hotelpracticejmix.entity.User;
import com.company.hotelpracticejmix.screen.registrationcard.RegistrationCardEdit;
import com.company.hotelpracticejmix.screen.registrationcardfragment.RegistrationCardFragment;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Fragments;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("HomeUserView")
@UiDescriptor("home-user-view.xml")
public class HomeUserView extends Screen {

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

    @Subscribe
    private void onInit(InitEvent event) {
        User user = dataManager
                .load(User.class)
                .condition(PropertyCondition
                        .equal("username", currentAuthentication.getUser().getUsername())).one();

       List<RegistrationCard> registrationCard = dataManager.load(RegistrationCard.class).condition(PropertyCondition.equal("user",user)).list();

       if(!registrationCard.isEmpty()){
           for (RegistrationCard card : registrationCard) {
               RegistrationCardFragment registrationCardFragment = fragments.create(this, RegistrationCardFragment.class);
               registrationCardFragment.setClientName(card.getClient().getDisplayName());
               registrationCardFragment.setApartanemtNumber(card.getApartaments().getDisplayName());
               registrationCardFragment.setDateDeparture(card.getDepartureDate());
               registrationCardFragment.setDateArrival(card.getArrivalDate());
               if(card.getResultsCovidTest() != null){
                   registrationCardFragment.setCovidResult(true);
               }
               registrationCardFragment.setPaymentIndication(card.getPaymentIndication());
               registrationCardFragment.setPrepaymentIndication(card.getPrepaymentIndication());
               registrationCardFragment.setUuid(card.getId());
               groupBoxLayout.add(registrationCardFragment.getFragment());
           }
       }
        bookHotel.setAction(new BaseAction("action")
                .withHandler(actionPerformedEvent ->
                        screenBuilders.editor(RegistrationCard.class, this)
                                .withScreenClass(RegistrationCardEdit.class)
                                .build().show()));
    }
}







