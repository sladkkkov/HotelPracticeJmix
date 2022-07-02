package com.company.hotelpracticejmix.screen.client;

import com.company.hotelpracticejmix.entity.Client;
import io.jmix.ui.screen.*;

@UiController("Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
public class ClientEdit extends StandardEditor<Client> {
}