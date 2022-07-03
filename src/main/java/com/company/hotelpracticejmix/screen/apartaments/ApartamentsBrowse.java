package com.company.hotelpracticejmix.screen.apartaments;

import com.company.hotelpracticejmix.service.BookingService;
import io.jmix.ui.action.Action;
import io.jmix.ui.screen.*;
import com.company.hotelpracticejmix.entity.Apartaments;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Apartaments.browse")
@UiDescriptor("apartaments-browse.xml")
@LookupComponent("apartamentsesTable")
public class ApartamentsBrowse extends StandardLookup<Apartaments> {

  /*  @Autowired
    private final BookingService bookingService;

    public ApartamentsBrowse(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Subscribe("apartamentsesTable.freeApartaments")
    public void actionFreeApartament(Action.ActionPerformedEvent event) {

    }*/
}