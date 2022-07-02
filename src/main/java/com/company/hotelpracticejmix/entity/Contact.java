package com.company.hotelpracticejmix.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JmixEntity
@Embeddable
public class Contact {
    @Column(name = "TELEPHONE")
    private String telephone;

    @Length(message = "Ваша почта нам не подходит", min = 3, max = 255)
    @NotNull(message = "Поле не должно быть пустым")
    @NotEmpty(message = "Поле не должно быть пустым")

    @Column(name = "EMAIL")
    private String email;

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}