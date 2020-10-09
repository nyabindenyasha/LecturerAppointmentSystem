package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.TimeSlots;
import com.lecturerappointmentsystem.repo.TimeSlotRepository;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
class TimeSlotServiceImpl extends BaseServiceImpl<TimeSlots, TimeSlots, TimeSlots> implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository) {
        super(timeSlotRepository);
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    protected Class<TimeSlots> getEntityClass() {
        return TimeSlots.class;
    }

    @Override
    public TimeSlots create(TimeSlots request) {

        val detailsExists = timeSlotRepository.existsByName(request.getName());

        if (detailsExists) {
            throw new InvalidRequestException("TimeSlot with the same name already exists");
        }

        val timeSlot = TimeSlots.fromCommand(request);

        return timeSlotRepository.save(timeSlot);
    }


    @Override
    public TimeSlots update(TimeSlots request) {

        val detailsExists = timeSlotRepository.existsByName(request.getName());

        if (!detailsExists) {
            throw new InvalidRequestException("TimeSlot not found");
        }

        val timeSlot = findById(request.getId());

        timeSlot.update(request);

        return timeSlotRepository.save(timeSlot);
    }

    @Override
    public void delete(Long id) {
        try {
            super.delete(id);
        } catch (ConstraintViolationException var3) {
            throw new InvalidRequestException("You can not delete this record is might be used by another record");
        }
    }
}

