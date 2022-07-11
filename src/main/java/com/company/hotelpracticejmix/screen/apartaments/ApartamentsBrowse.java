package com.company.hotelpracticejmix.screen.apartaments;

import com.company.hotelpracticejmix.entity.Apartaments;
import com.company.hotelpracticejmix.entity.RegistrationCard;
import com.company.hotelpracticejmix.service.RegistrationCardService;
import io.jmix.ui.UiComponents;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.*;
import io.jmix.ui.component.calendar.SimpleCalendarEvent;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@UiController("Apartaments.browse")
@UiDescriptor("apartaments-browse.xml")
@LookupComponent("apartmentsTable")
public class ApartamentsBrowse extends StandardLookup<Apartaments> {
    @Autowired
    private DataGrid<Apartaments> apartmentsTable;

    @Autowired
    private RegistrationCardService registrationCardService;

    @Autowired
    private UiComponents uiComponents;


    /**
     * По нажатию на элемент таблицы, отображаем детали для текущего элемента
     */
    @Subscribe
    public void onInit(InitEvent event) {
        apartmentsTable.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(actionPerformedEvent ->
                        apartmentsTable.setDetailsVisible(apartmentsTable.getSingleSelected(), true)));
    }

    /**
     * Метод добавляющий календарь для каждого Апартамента на layout
     */
    @Install(to = "apartmentsTable", subject = "detailsGenerator")
    public Component ordersDataGridDetailsGenerator(Apartaments apartaments) {
        VBoxLayout mainLayout = uiComponents.create(VBoxLayout.class);
        mainLayout.setWidth("100%");
        mainLayout.setMargin(true);

        HBoxLayout headerBox = uiComponents.create(HBoxLayout.class);
        headerBox.setWidth("100%");

        Label<String> infoLabel = uiComponents.create(Label.TYPE_STRING);
        Component calendar = createCalendar(apartaments);
        headerBox.add(calendar);
        Component closeButton = createCloseButton(apartaments);
        headerBox.add(infoLabel);
        headerBox.add(closeButton);
        headerBox.expand(infoLabel);

        mainLayout.add(headerBox);

        return mainLayout;
    }

    /**
     * Метод генерирующий календарь для каждого Апартамента, со списком заселений из соответствующий этому апартаменту RegistrationCard
     */
    public Component createCalendar(Apartaments apartaments) {
        Calendar<Date> calendar = uiComponents.create(Calendar.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendar.setStartDate(simpleDateFormat.parse(String.valueOf(LocalDate.now()), new ParsePosition(0)));
        calendar.setEndDate(simpleDateFormat.parse(String.valueOf(LocalDate.now().plusMonths(1)), new ParsePosition(0)));

        for (RegistrationCard registrationCard : registrationCardService.getRegistrationCardsByApartment(apartaments)) {
            calendar.getEventProvider().addEvent(calendarAddEvent(registrationCard));
        }

        return calendar;
    }

    /**
     * Выносим создание CalendarEvent в отдельный метод.
     */
    public SimpleCalendarEvent<Date> calendarAddEvent(RegistrationCard registrationCard) {
        SimpleCalendarEvent<Date> simpleCalendarEvent = new SimpleCalendarEvent<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        simpleCalendarEvent.setCaption(registrationCard.getArrivalDate() + registrationCard.getDepartureDate().toString());
        simpleCalendarEvent.setStart(simpleDateFormat.parse(registrationCard.getArrivalDate().toString(), new ParsePosition(0)));
        simpleCalendarEvent.setEnd(simpleDateFormat.parse(registrationCard.getDepartureDate().toString(), new ParsePosition(0)));
        simpleCalendarEvent.setAllDay(false);

        return simpleCalendarEvent;
    }

    /**
     * Создание кнопки для закрытия календаря для каждого апартамента
     */
    public Component createCloseButton(Apartaments apartaments) {
        Button closeButton = uiComponents.create(Button.class);
        closeButton.setIcon("font-icon:TIMES");
        BaseAction closeAction = new BaseAction("closeAction")
                .withHandler(actionPerformedEvent ->
                        apartmentsTable.setDetailsVisible(apartaments, false));
        closeButton.setAction(closeAction);
        return closeButton;
    }
}
