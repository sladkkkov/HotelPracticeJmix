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

}
