package com.company.hotelpracticejmix.screen.registrationcardfragment;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.Label;
import io.jmix.ui.component.TextField;
import io.jmix.ui.component.Timer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

@UiController("RegistrationCardFragment")
@UiDescriptor("registration-card-fragment.xml")
public class RegistrationCardFragment extends ScreenFragment {
    @Autowired
    protected Timer timer;

    protected int seconds = 0;
    @Autowired
    private Notifications notifications;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

    @Autowired
    private TextField<String> clientNameLabel;
    @Autowired
    private TextField<String> apartamentNumberLabel;
    @Autowired
    private TextField<String> dateArrivalLabel;

    @Autowired
    private CheckBox paymentCheckBox;
    @Autowired
    private CheckBox prepaymentCheckBox;
    @Autowired
    private CheckBox covidResultCheckBox;
    @Autowired
    private TextField<String> dateDepartureLabel;

    @Autowired
    DataManager dataManager;
    private String clientName;
    private LocalDate dateArrival;

    public void setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    private LocalDate dateDeparture;
    private String apartanemtNumber;

    private Boolean paymentIndication;
    private Boolean prepaymentIndication;

    public void setPaymentIndication(Boolean paymentIndication) {
        this.paymentIndication = paymentIndication;
    }

    public void setPrepaymentIndication(Boolean prepaymentIndication) {
        this.prepaymentIndication = prepaymentIndication;
    }

    public void setCovidResult(Boolean covidResult) {
        this.covidResult = covidResult;
    }

    private Boolean covidResult;

    public void setApartanemtNumber(String apartanemtNumber) {
        this.apartanemtNumber = apartanemtNumber;
    }

    public void setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    @Subscribe
    public void onInit(InitEvent event) {
        clientNameLabel.setValue(clientName);
        apartamentNumberLabel.setValue(apartanemtNumber);
        dateArrivalLabel.setValue(String.valueOf(dateArrival));
        dateDepartureLabel.setValue(String.valueOf(dateDeparture));
        covidResultCheckBox.setValue(covidResult);
        paymentCheckBox.setValue(paymentIndication);
        prepaymentCheckBox.setValue(prepaymentIndication);
    }

    @Subscribe("timer")
    protected void onTimerFacetTick(Timer.TimerActionEvent event) {
        seconds += event.getSource().getDelay() / 1000;
        dataManager.remove(Id.of(uuid, RegistrationCard.class));

        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption("Бронирование было отменено. Вы не произвели предоплату в течении суток.")
                .show();
    }
}