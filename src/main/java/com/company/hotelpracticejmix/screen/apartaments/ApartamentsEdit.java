package com.company.hotelpracticejmix.screen.apartaments;

import com.company.hotelpracticejmix.entity.Apartaments;
import io.jmix.ui.screen.*;

@UiController("Apartaments.edit")
@UiDescriptor("apartaments-edit.xml")
@EditedEntityContainer("apartamentsDc")
public class ApartamentsEdit extends StandardEditor<Apartaments> {
}