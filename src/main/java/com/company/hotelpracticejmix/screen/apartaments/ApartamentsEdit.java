package com.company.hotelpracticejmix.screen.apartaments;

import com.company.hotelpracticejmix.entity.Apartaments;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("Apartaments.edit")
@UiDescriptor("apartaments-edit.xml")
@EditedEntityContainer("apartamentsDc")
public class ApartamentsEdit extends StandardEditor<Apartaments> {


}