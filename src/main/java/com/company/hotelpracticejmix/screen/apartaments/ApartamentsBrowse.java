package com.company.hotelpracticejmix.screen.apartaments;

import com.company.hotelpracticejmix.entity.Apartaments;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

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
/*
    @Autowired
    protected DataGrid<Apartaments> ordersDataGrid;
*/

  /*@Subscribe
    protected void onInit(InitEvent event) {
        ordersDataGrid.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(actionPerformedEvent ->
                        ordersDataGrid.setDetailsVisible(ordersDataGrid.getSingleSelected(), true)));
    }*/

}
