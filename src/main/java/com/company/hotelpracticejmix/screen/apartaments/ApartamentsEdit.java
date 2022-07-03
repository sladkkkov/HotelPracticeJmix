package com.company.hotelpracticejmix.screen.apartaments;

import io.jmix.ui.screen.*;
import com.company.hotelpracticejmix.entity.Apartaments;

@UiController("Apartaments.edit")
@UiDescriptor("apartaments-edit.xml")
@EditedEntityContainer("apartamentsDc")
public class ApartamentsEdit extends StandardEditor<Apartaments> {
}