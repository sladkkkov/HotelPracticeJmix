package com.company.hotelpracticejmix.screen.registrationcardfragment;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import com.company.hotelpracticejmix.service.RegistrationCardService;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
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

    @Autowired
    DataManager dataManager;

    @Autowired
    private Messages messages;

    @Autowired
    private TextField<String> clientNameLabel;
    @Autowired
    private RegistrationCardService registrationCardService;
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


    private Boolean covidResult;
    private UUID uuid;
    private String clientName;
    private LocalDate dateArrival;
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

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

    public void setDateDeparture(LocalDate dateDeparture) {
        this.dateDeparture = dateDeparture;
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

    public void showStandardNotification(String message) {
        notifications.create(Notifications.NotificationType.TRAY)
                .withCaption(message)
                .show();
    }

    @Subscribe("timer")
    protected void onTimerFacetTick(Timer.TimerActionEvent event) {
        seconds += event.getSource().getDelay() / 1000;
        labelTimer.setValue(LocalTime.ofSecondOfDay(Duration.ofDays(1).getSeconds() - seconds) + " " + messages.getMessage("localization/makePayment"));

        if (seconds == Duration.ofDays(1).getSeconds()) {

            registrationCardService.deleteRegistrationCardById(uuid);

            showStandardNotification(messages.getMessage("localization/cancelBooking"));
        }
    }


    @Subscribe("paymentButton.paymentAction")
    public void onPaymentButtonPaymentAction(Action.ActionPerformedEvent event) {

        RegistrationCard registrationCard = registrationCardService.getRegistrationCardByUuid(uuid);


        if (registrationCard.getPrepaymentIndication().equals(true) && registrationCard.getPaymentIndication().equals(false)) {

            createStandardDialogWithCaptionAndMessage(messages.getMessage("localization/successful"), messages.getMessage("localization/paymentAccepted"));
            registrationCardService.updateRegistrationCardWithPaymentStatus(uuid, true);

        } else {

            showStandardNotification(messages.getMessage("localization/paymentAlreadyMade"));
        }
    }


    public void createStandardDialogWithCaptionAndMessage(String caption, String message) {
        dialogs.createMessageDialog()
                .withCaption(caption)
                .withMessage(message)
                .show();
    }

    @Subscribe("paymentButton.allPaymentAction")
    public void onPaymentButtonAllPaymentAction(Action.ActionPerformedEvent event) {
        RegistrationCard registrationCard = registrationCardService.getRegistrationCardByUuid(uuid);

        if (registrationCard.getPrepaymentIndication().equals(true) || registrationCard.getPaymentIndication().equals(true)) {
            showStandardNotification(messages.getMessage("localization/paymentAlreadyDetected"));
        } else {
            createStandardDialogWithCaptionAndMessage(messages.getMessage("localization/successful"), messages.getMessage("localization/paymentAccepted"));

            registrationCard.setPaymentIndication(true);
            registrationCard.setPrepaymentIndication(true);
            registrationCard.setPrepaymentDate(LocalDate.now());
            registrationCard.setPaymentDate(LocalDate.now());
            dataManager.save(registrationCard);
        }
    }

    @Subscribe("paymentButton.prepaymentAction")
    public void onPaymentButtonPrepaymentAction(Action.ActionPerformedEvent event) {

        RegistrationCard registrationCard = registrationCardService.getRegistrationCardByUuid(uuid);

        if (registrationCard.getPrepaymentIndication().equals(false)) {

            createStandardDialogWithCaptionAndMessage(messages.getMessage("localization/successful"), messages.getMessage("localization/paymentAccepted"));

            registrationCardService.updateRegistrationCardWithPrepaymentStatus(uuid, true);

        } else {

            showStandardNotification(messages.getMessage("localization/prepaymentAlreadyMade"));

        }
    }

    @Subscribe("paymentButton.reschedule")
    public void onPaymentButtonReschedule(Action.ActionPerformedEvent event) {
        RegistrationCard registrationCard = registrationCardService.getRegistrationCardByUuid(uuid);
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

                                        registrationCardService.changeArrivalAndDepartureDate(uuid, ((Date) dialog.getValue("arrivalDate")).toLocalDate(),
                                                ((Date) dialog.getValue("departureDate")).toLocalDate());

                                        dialog.closeWithDefaultAction();
                                    }))
                    .show();
        } else {
            showStandardNotification(messages.getMessage("localization/noo"));
        }
    }


    @Subscribe("prepaymentCheckBox")
    public void onPrepaymentCheckBoxValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        timer.stop();

        registrationCardService.updateRegistrationCardWithPrepaymentStatus(uuid, true);

        labelTimer.setValue(messages.getMessage("localization/prepaymentTaken"));

        showStandardNotification(messages.getMessage("localization/prepaymentTaken"));
    }
}