package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(targetEntity = EmployeeEntity.class)
    private List<EmployeeEntity> employee;

    @ManyToMany(targetEntity = PetEntity.class)
    private List<PetEntity> pet;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public List<EmployeeEntity> getEmployee() {
        return this.employee;
    }

    public void setEmployee(List<EmployeeEntity> employee) {
        this.employee = employee;
    }

    public List<PetEntity> getPet() {
        return this.pet;
    }

    public void setPet(List<PetEntity> pet) {
        this.pet = pet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
