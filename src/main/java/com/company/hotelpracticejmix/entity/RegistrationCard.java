package com.company.hotelpracticejmix.entity;

import io.jmix.core.DeletePolicy;
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

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @OnDelete(DeletePolicy.CASCADE)
    @JoinColumn(name = "APARTAMENTS_ID")
    private Apartaments apartaments;

    @Column(name = "RESULTS_COVID_TEST")
    private Boolean resultsCovidTest;

    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;

    @Column(name = "PREPAYMENT_DATE")
    private LocalDate prepaymentDate;

    @Column(name = "PREPAYMENT_INDICATION")
    private Boolean prepaymentIndication;

    @Column(name = "PAYMENT_INDICATION")
    private Boolean paymentIndication;

    @Column(name = "ARRIVAL_DATE")
    private LocalDate arrivalDate;

    @Column(name = "DEPARTURE_DATE")
    private LocalDate departureDate;

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

    public Boolean getResultsCovidTest() {
        return resultsCovidTest;
    }

    public void setResultsCovidTest(Boolean resultsCovidTest) {
        this.resultsCovidTest = resultsCovidTest;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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