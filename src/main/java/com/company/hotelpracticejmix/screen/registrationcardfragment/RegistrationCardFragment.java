package com.company.hotelpracticejmix.screen.registrationcardfragment;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.Messages;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.app.inputdialog.DialogActions;
import io.jmix.ui.app.inputdialog.DialogOutcome;
import io.jmix.ui.app.inputdialog.InputDialog;
import io.jmix.ui.app.inputdialog.InputParameter;
import io.jmix.ui.component.*;
import io.jmix.ui.component.inputdialog.InputDialogAction;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
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
    @Autowired
    private Messages messages;

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
        labelTimer.setValue(LocalTime.ofSecondOfDay(Duration.ofDays(1).getSeconds() - seconds) + messages.getMessage("localization/makePayment"));

        if (seconds == Duration.ofDays(1).getSeconds()) {
            dataManager.remove(Id.of(uuid, RegistrationCard.class));

            showNotification(messages.getMessage("localization/cancelBooking"));
        }
    }


    @Subscribe("paymentButton.paymentAction")
    public void onPaymentButtonPaymentAction(Action.ActionPerformedEvent event) {
        RegistrationCard registrationCard = dataManager.load(RegistrationCard.class).id(uuid).one();
        if (registrationCard.getPaymentIndication().equals(true)) {
            showNotification(messages.getMessage("localization/paymentAlreadyMade"));
        } else {
            if (registrationCard.getPrepaymentIndication().equals(true)) {

                dialogs.createMessageDialog()
                        .withCaption(messages.getMessage("localization/successful"))
                        .withMessage(messages.getMessage("localization/paymentAccepted"))
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
            showNotification(messages.getMessage("localization/paymentAlreadyDetected"));
        } else {
            dialogs.createMessageDialog()
                    .withCaption(messages.getMessage("localization/successful"))
                    .withMessage(messages.getMessage("localization/paymentAccepted"))
                    .show();

            registrationCard.setPaymentIndication(true);
            registrationCard.setPrepaymentIndication(true);
            registrationCard.setPrepaymentDate(LocalDate.now());
            registrationCard.setPaymentDate(LocalDate.now());
            dataManager.save(registrationCard);
        }
    }

    @Subscribe("paymentButton.reschedule")
    public void onPaymentButtonReschedule(Action.ActionPerformedEvent event) {
        RegistrationCard registrationCard = dataManager.load(RegistrationCard.class).id(uuid).one();
        if (registrationCard.getPaymentIndication().equals(true)) {
            dialogs.createInputDialog(this)
                    .withCaption(messages.getMessage("localization/paymentAccepted"))
                    .withParameters(
                            InputParameter.dateParameter("arrivalDate")
                                    .withCaption(messages.getMessage("localization/arrivalDate"))
                                    .withRequired(true),
                            InputParameter.dateParameter("departureDate")
                                    .withCaption(messages.getMessage("localization/departureDate"))
                                    .withRequired(true)
                    )
                    .withActions(
                            InputDialogAction.action("confirm")
                                    .withCaption(messages.getMessage("localization/accept"))
                                    .withPrimary(true)
                                    .withHandler(actionEvent -> {
                                        InputDialog dialog = actionEvent.getInputDialog();

                                        registrationCard.setArrivalDate(((Date) dialog.getValue("arrivalDate")).toLocalDate());
                                        registrationCard.setDepartureDate(((Date) dialog.getValue("departureDate")).toLocalDate());
                                        dataManager.save(registrationCard);

                                        dialog.closeWithDefaultAction();
                                    }))
                    .show();
        }
        else {
            showNotification(messages.getMessage("localization/noo"));
        }
    }

    @Subscribe("paymentButton.prepaymentAction")
    public void onPaymentButtonPrepaymentAction(Action.ActionPerformedEvent event) {

        RegistrationCard registrationCard = dataManager.load(RegistrationCard.class).id(uuid).one();
        if (registrationCard.getPrepaymentIndication().equals(true)) {
            showNotification(messages.getMessage("localization/prepaymentAlreadyMade"));
        } else {

            dialogs.createMessageDialog()
                    .withCaption(messages.getMessage("localization/successful"))
                    .withMessage(messages.getMessage("localization/paymentAccepted"))
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

        labelTimer.setValue(messages.getMessage("localization/prepaymentTaken"));

        showNotification(messages.getMessage("localization/prepaymentTaken"));
    }
}