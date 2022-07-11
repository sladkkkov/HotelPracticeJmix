package com.company.hotelpracticejmix.service;

import com.company.hotelpracticejmix.entity.RegistrationCard;
import com.company.hotelpracticejmix.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    /**
     * Метод получения экземпляра User из бина CurrentAuthentication и его преобразование из UserDetails в сущность User
     */
    public User getUserByCurrentAuthentication() {
        return dataManager
                .load(User.class)
                .condition(PropertyCondition
                        .equal("username", currentAuthentication.getUser().getUsername())).one();
    }

    /**
     * Метод получения списка RegistrationCard по конкретному Апартаменту
     */
    public void setUserByRegistrationCardIfNotAdmin(RegistrationCard registrationCard) {
        String fullAccessRole = "system-full-access";
        if (!getRoleName(currentAuthentication.getAuthentication()).equals(fullAccessRole)) {
            registrationCard.setUser(getUserByCurrentAuthentication());
            dataManager.save(registrationCard);
        }
    }

    /**
     * Метод получения роли User из Authentication
     */
    private String getRoleName(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

}
