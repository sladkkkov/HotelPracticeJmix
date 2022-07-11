package com.company.hotelpracticejmix.service;

import com.company.hotelpracticejmix.entity.Apartaments;
import com.company.hotelpracticejmix.entity.RegistrationCard;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationCardService {
    @Autowired
    private UserService userService;
    @Autowired
    private DataManager dataManager;

    /**
     * Метод получения списка RegistrationCard по конкретному Апартаменту
     */
    public List<RegistrationCard> getRegistrationCardsByApartment(Apartaments apartaments) {
        return dataManager
                .load(RegistrationCard.class)
                .condition(PropertyCondition
                        .equal("apartaments", apartaments)).list();
    }
    public List<RegistrationCard> getRegistrationCardsByCurrentAuthentication() {
        return dataManager
                .load(RegistrationCard.class)
                .condition(PropertyCondition
                        .equal("user", userService.getUserByCurrentAuthentication())).list();
    }
    /**
     * Метод получения списка Карточек Регистрации по UUID карточки регистрации
     */
    public RegistrationCard getRegistrationCardByUuid(UUID uuid) {
        return dataManager.load(RegistrationCard.class).id(uuid).one();
    }

    /**
     * Метод обновления полей prepaymentIndication и prepaymentDate
     */
    public void updateRegistrationCardWithPrepaymentStatus(UUID uuid, Boolean paymentIndication) {

        RegistrationCard registrationCard = getRegistrationCardByUuid(uuid);
        registrationCard.setPrepaymentIndication(paymentIndication);
        registrationCard.setPrepaymentDate(LocalDate.now());

        dataManager.save(registrationCard);
    }

    /**
     * Метод обновления полей paymentIndication и paymentDate
     */
    public void updateRegistrationCardWithPaymentStatus(UUID uuid, Boolean paymentIndication) {

        RegistrationCard registrationCard = getRegistrationCardByUuid(uuid);
        registrationCard.setPaymentIndication(paymentIndication);
        registrationCard.setPaymentDate(LocalDate.now());

        dataManager.save(registrationCard);
    }

    /**
     * Метод для изменения даты прибытия и отъезда
     */
    public void changeArrivalAndDepartureDate(UUID uuid, LocalDate arrivalDate, LocalDate departureDate) {
        RegistrationCard registrationCard = getRegistrationCardByUuid(uuid);
        registrationCard.setArrivalDate(arrivalDate);
        registrationCard.setDepartureDate(departureDate);
        dataManager.save(registrationCard);
    }

    /**
     * Метод для удаления сущности RegistrationCard по UUID
     */
    public void deleteRegistrationCardById(UUID uuid) {
        dataManager.remove(Id.of(uuid, RegistrationCard.class));
    }
}
