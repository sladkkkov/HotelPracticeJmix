package com.company.hotelpracticejmix.service;

import com.company.hotelpracticejmix.entity.Apartaments;
import com.company.hotelpracticejmix.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
public class BookingService {


    @Autowired
    private DataManager dataManager;

    List<Apartaments> setCustomerCodeInSession(String code) {
        return dataManager.load(Apartaments.class).condition(LogicalCondition.and
                (PropertyCondition.equal("apartament_reservation", false))).list();
    }

    @EventListener
    public void onApartamentsLoading(EntityLoadingEvent<Apartaments> event) {

    }
}