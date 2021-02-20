package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity{

    private String phoneNumber;

    private String notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerEntity", cascade = CascadeType.ALL, targetEntity = PetEntity.class)
    private List<PetEntity> pet;

    public CustomerEntity() {
    };

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PetEntity> getPet() {
        return this.pet;
    }

    public void setPet(List<PetEntity> pet) {
        this.pet = pet;
    }
}
