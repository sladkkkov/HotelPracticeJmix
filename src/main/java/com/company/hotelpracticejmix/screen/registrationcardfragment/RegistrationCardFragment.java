package com.company.hotelpracticejmix.screen.registrationcardfragment;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@UiController("RegistrationCardFragment")
@UiDescriptor("registration-card-fragment.xml")
public class RegistrationCardFragment extends ScreenFragment {
    @Autowired
    private Timer timer;

    private long seconds = 0;

    @Autowired
    private Dialogs dialogs;

    @Autowired
    private Label<String> labelTimer;
    @Autowired
    private Notifications notifications;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    private UUID uuid;

    @Autowired
    private TextField<String> clientNameLabel;

    @Autowired
    private TextField<String> apartmentNumberLabel;
    @Autowired
    private TextField<String> dateArrivalLabel;

    @Autowired
    private CheckBox paymentCheckBox;

    @Autowired
    private Label<String> covidCheckLabel;
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

    private String covidValidationResult;
    private String apartmentNumber;
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

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setCovidValidationResult(String covidValidationResult) {
        this.covidValidationResult = covidValidationResult;
    }

    @Subscribe
    public void onInit(InitEvent event) {
        clientNameLabel.setValue(clientName);
        apartmentNumberLabel.setValue(apartmentNumber);
        dateArrivalLabel.setValue(String.valueOf(dateArrival));
        dateDepartureLabel.setValue(String.valueOf(dateDeparture));
        covidResultCheckBox.setValue(covidResult);
        paymentCheckBox.setValue(paymentIndication);
        prepaymentCheckBox.setValue(prepaymentIndication);
        covidCheckLabel.setValue(covidValidationResult);
    }

    public void showNotification(String message) {
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(message)
                .show();
    }

    @Subscribe("timer")
    protected void onTimerFacetTick(Timer.TimerActionEvent event) {
        seconds += event.getSource().getDelay() / 1000;
        labelTimer.setValue(LocalTime.ofSecondOfDay(Duration.ofDays(1).getSeconds() - seconds) + " внесите предоплату.");

        if (seconds == Duration.ofDays(1).getSeconds()) {
            dataManager.remove(Id.of(uuid, RegistrationCard.class));

            showNotification("Бронирование было отменено. Вы не произвели предоплату в течении суток.");
        }
    }


    @Subscribe("paymentButton.paymentAction")
    public void onPaymentButtonPaymentAction(Action.ActionPerformedEvent event) {
        RegistrationCard registrationCard = dataManager.load(RegistrationCard.class).id(uuid).one();
        if (registrationCard.getPaymentIndication().equals(true)) {
            showNotification("Оплата уже внесена");
        } else {
            if (registrationCard.getPrepaymentIndication().equals(true)) {

                dialogs.createMessageDialog()
                        .withCaption("Успешно")
                        .withMessage("Ваша оплата принята")
                        .show();

                registrationCard.setPaymentIndication(true);
                registrationCard.setPaymentDate(LocalDate.now());
                dataManager.save(registrationCard);
            }
        }

    }

    @Subscribe("paymentButton.allPaymentAction")
    public void onPaymentButtonAllPaymentAction(Action.ActionPerformedEvent event) {
        RegistrationCard registrationCard = dataManager.load(RegistrationCard.class).id(uuid).one();
        if (registrationCard.getPrepaymentIndication().equals(true) || registrationCard.getPaymentIndication().equals(true)) {
            showNotification("Ваш заказ уже частично оплачен");
        } else {
            dialogs.createMessageDialog()
                    .withCaption("Успешно")
                    .withMessage("Ваша оплата принята")
                    .show();

            registrationCard.setPaymentIndication(true);
            registrationCard.setPrepaymentIndication(true);
            registrationCard.setPrepaymentDate(LocalDate.now());
            registrationCard.setPaymentDate(LocalDate.now());
            dataManager.save(registrationCard);
        }
    }

    @Subscribe("paymentButton.prepaymentAction")
    public void onPaymentButtonPrepaymentAction(Action.ActionPerformedEvent event) {

        RegistrationCard registrationCard = dataManager.load(RegistrationCard.class).id(uuid).one();
        if (registrationCard.getPrepaymentIndication().equals(true)) {
            showNotification("Предоплата уже внесена");
        } else {

            dialogs.createMessageDialog()
                    .withCaption("Успешно")
                    .withMessage("Ваша оплата принята")
                    .show();

            registrationCard.setPrepaymentIndication(true);
            registrationCard.setPrepaymentDate(LocalDate.now());
            dataManager.save(registrationCard);
        }
    }

    @Subscribe("prepaymentCheckBox")
    public void onPrepaymentCheckBoxValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        timer.stop();

        RegistrationCard registrationCard = dataManager.load(RegistrationCard.class).id(uuid).one();
        registrationCard.setPrepaymentIndication(true);
        registrationCard.setPrepaymentDate(LocalDate.now());
        dataManager.save(registrationCard);

        labelTimer.setValue("Предоплата произведена");

        showNotification("Предоплата произведена");
    }
}