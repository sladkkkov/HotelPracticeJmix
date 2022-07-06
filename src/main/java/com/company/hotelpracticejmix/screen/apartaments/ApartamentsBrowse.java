package com.company.hotelpracticejmix.screen.apartaments;

import com.company.hotelpracticejmix.entity.Apartaments;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
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

  /*@Subscribe
    protected void onInit(InitEvent event) {
        ordersDataGrid.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(actionPerformedEvent ->
                        ordersDataGrid.setDetailsVisible(ordersDataGrid.getSingleSelected(), true)));
    }*/


}
