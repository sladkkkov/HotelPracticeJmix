package com.company.hotelpracticejmix.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.FileRef;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDelete;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "REGISTRATION_CARD", indexes = {
        @Index(name = "IDX_REGISTRATIONCARD", columnList = "CLIENT_ID"),
        @Index(name = "IDX_REGISTRATIONCARD", columnList = "APARTAMENTS_ID")
})
@Entity
public class RegistrationCard {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @NotNull
    @OnDeleteInverse(DeletePolicy.DENY)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "APARTAMENTS_ID")
    private Apartaments apartaments;


    @NotNull(message = "{msg://com.company.hotelpracticejmix.entity/RegistrationCard.resultsCovidTest.validation.NotNull}")
    @Column(name = "RESULTS_COVID_TEST")
    private FileRef resultsCovidTest;

    @NotNull
    @PastOrPresent(message = "{msg://com.company.hotelpracticejmix.entity/RegistrationCard.paymentDate.validation.PastOrPresent}")
    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

    @NotNull
    @PastOrPresent(message = "{msg://com.company.hotelpracticejmix.entity/RegistrationCard.prepaymentDate.validation.PastOrPresent}")
    @Column(name = "PREPAYMENT_DATE")
    private LocalDate prepaymentDate;

    @Column(name = "PREPAYMENT_INDICATION")
    private Boolean prepaymentIndication;

    @Column(name = "PAYMENT_INDICATION")
    private Boolean paymentIndication;

    @NotNull
    @FutureOrPresent(message = "{msg://com.company.hotelpracticejmix.entity/RegistrationCard.arrivalDate.validation.FutureOrPresent}")
    @Column(name = "ARRIVAL_DATE")
    private LocalDate arrivalDate;

    @NotNull
    @Future(message = "{msg://com.company.hotelpracticejmix.entity/RegistrationCard.departureDate.validation.Future}")
    @Column(name = "DEPARTURE_DATE")
    private LocalDate departureDate;

    public void setUser(User user) {
        this.user = user;
    }

    public void setResultsCovidTest(FileRef resultsCovidTest) {
        this.resultsCovidTest = resultsCovidTest;
    }

    public @NotNull(message = "{msg://com.company.hotelpracticejmix.entity/RegistrationCard.resultsCovidTest.validation.NotNull}") FileRef getResultsCovidTest() {
        return resultsCovidTest;
    }


    public void setApartaments(Apartaments apartaments) {
        this.apartaments = apartaments;
    }

    public Apartaments getApartaments() {
        return apartaments;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Boolean getPaymentIndication() {
        return paymentIndication;
    }

    public void setPaymentIndication(Boolean paymentIndication) {
        this.paymentIndication = paymentIndication;
    }

    public Boolean getPrepaymentIndication() {
        return prepaymentIndication;
    }

    public void setPrepaymentIndication(Boolean prepaymentIndication) {
        this.prepaymentIndication = prepaymentIndication;
    }

    public LocalDate getPrepaymentDate() {
        return prepaymentDate;
    }

    public void setPrepaymentDate(LocalDate prepaymentDate) {
        this.prepaymentDate = prepaymentDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }



    public User getClient() {
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setClient(User user) {
        this.user = user;
    }


    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}