package com.company.hotelpracticejmix.service;

import com.company.hotelpracticejmix.entity.Apartaments;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {


    @Autowired
    private DataManager dataManager;

   public List<Apartaments> getFreeApartament() {
        return dataManager.load(Apartaments.class).condition(LogicalCondition.and
                (PropertyCondition.equal("apartament_reservation", false))).list();
    }

}