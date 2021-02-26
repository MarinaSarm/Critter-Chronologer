package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Long createSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity).getId();
    }

    public List<ScheduleEntity> getAll() {
        return scheduleRepository.findAll();
    }
}
