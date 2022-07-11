package com.company.hotelpracticejmix.screen.registrationcard;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("RegistrationCard.browse")
@UiDescriptor("registration-card-browse.xml")
@LookupComponent("registrationCardsTable")
public class RegistrationCardBrowse extends StandardLookup<RegistrationCard> {
    @Autowired
    private Table<RegistrationCard> registrationCardsTable;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private Button validationCovidBtn;

    private void editSelectedEntity(RegistrationCard entity) {
        validationCovidBtn.setAction(new BaseAction("action")
                .withHandler(actionPerformedEvent ->
                        screenBuilders.editor(RegistrationCard.class, this)
                                .withScreenClass(RegistrationCardCovidEdit.class).editEntity(entity)
                                .build().show()));
    }


    /**
     * Метод открывает по нажатию кнопки экран редактирования сущности RegistrationCard
     */
    @Subscribe("validationCovidBtn")
    public void onValidationCovidBtnClick(Button.ClickEvent event) {
        editSelectedEntity(registrationCardsTable.getSingleSelected());
    }

}