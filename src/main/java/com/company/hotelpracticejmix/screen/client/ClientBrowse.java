package com.company.hotelpracticejmix.screen.client;

import com.company.hotelpracticejmix.entity.Client;
import io.jmix.ui.screen.*;

@UiController("Client.browse")
@UiDescriptor("client-browse.xml")
@LookupComponent("clientsTable")
public class ClientBrowse extends StandardLookup<Client> {
}