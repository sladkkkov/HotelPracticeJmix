package com.company.hotelpracticejmix.screen.homeview;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import com.company.hotelpracticejmix.screen.registrationcard.RegistrationCardEdit;
import com.company.hotelpracticejmix.screen.registrationcardfragment.RegistrationCardFragment;
import com.company.hotelpracticejmix.service.RegistrationCardService;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.ui.Fragments;
import io.jmix.ui.Notifications;
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
    private RegistrationCardService registrationCardService;
    @Autowired
    private DataManager dataManager;

    @Autowired
    private Notifications notifications;

    /**
     * Метод для создания фрагмента по RegistrationCard
     */
    public void createRegistrationCardFragmentsByEditedEntity(RegistrationCard registrationCard) {
        RegistrationCardFragment registrationCardFragment = fragments.create(this, RegistrationCardFragment.class);

        registrationCardFragment.setClientName(registrationCard.getClient().getDisplayName());
        registrationCardFragment.setApartmentNumber(registrationCard.getApartaments().getDisplayName());
        registrationCardFragment.setDateDeparture(registrationCard.getDepartureDate());
        registrationCardFragment.setDateArrival(registrationCard.getArrivalDate());
        registrationCardFragment.setPaymentIndication(registrationCard.getPaymentIndication());
        registrationCardFragment.setPrepaymentIndication(registrationCard.getPrepaymentIndication());
        registrationCardFragment.setUuid(registrationCard.getId());

        if (registrationCard.getResultsCovidTestValidation().equals(false)) {
            registrationCardFragment.setCovidResult(false);
            registrationCardFragment.setCovidValidationResult(messages.getMessage("localization/validationTest"));

        } else {
            registrationCardFragment.setCovidResult(true);
            registrationCardFragment.setCovidValidationResult(messages.getMessage("localization/acceptTest"));
        }
        groupBoxLayout.add(registrationCardFragment.getFragment());

    }

    /**
     * Метод для добавления фрагментов для всех заказов из текущей авторизации
     */
    public void getRegistrationCardFragments() {
        List<RegistrationCard> list = registrationCardService.getRegistrationCardsByCurrentAuthentication();
        if (list.isEmpty()) {
            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption(messages.getMessage("localization/orderNotFound"))
                    .show();
            for (RegistrationCard card : registrationCardService.getRegistrationCardsByCurrentAuthentication()) {
                createRegistrationCardFragmentsByEditedEntity(card);
            }
        }
    }

    /**
     * Метод инициализации экрана
     */
    private void createBookHotelButton() {
        RegistrationCardEdit registrationCardEdit =
                screenBuilders
                        .screen(this)
                        .withScreenClass(RegistrationCardEdit.class)
                        .build();

        registrationCardEdit.addAfterCloseListener(afterCloseEvent -> {
            createRegistrationCardFragmentsByEditedEntity(registrationCardEdit.getEditedEntity());
        });

        registrationCardEdit.setEntityToEdit(dataManager.create(RegistrationCard.class));

        addActionBookHotelButton(registrationCardEdit);
    }

    /**
     * Метод привязывает действие открытия окна RegistrationCardEdit к кнопке
     */
    public void addActionBookHotelButton(RegistrationCardEdit registrationCardEdit) {
        bookHotel.setAction(new BaseAction("action")
                .withHandler(actionPerformedEvent -> registrationCardEdit.show()));
    }


    @Subscribe
    private void onInit(InitEvent event) {
        getRegistrationCardFragments();
        createBookHotelButton();

    }


}











