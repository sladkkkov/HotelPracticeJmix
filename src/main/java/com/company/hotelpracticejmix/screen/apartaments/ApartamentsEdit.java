package com.company.hotelpracticejmix.screen.apartaments;

import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.hotelpracticejmix.entity.Apartaments;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Apartaments.edit")
@UiDescriptor("apartaments-edit.xml")
@EditedEntityContainer("apartamentsDc")
public class ApartamentsEdit extends StandardEditor<Apartaments> {


}