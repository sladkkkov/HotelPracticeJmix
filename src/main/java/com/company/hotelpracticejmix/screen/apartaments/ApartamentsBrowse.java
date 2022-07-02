package com.company.hotelpracticejmix.screen.apartaments;

import com.company.hotelpracticejmix.entity.Apartaments;
import io.jmix.ui.screen.*;

@UiController("Apartaments.browse")
@UiDescriptor("apartaments-browse.xml")
@LookupComponent("apartamentsesTable")
public class ApartamentsBrowse extends StandardLookup<Apartaments> {

}