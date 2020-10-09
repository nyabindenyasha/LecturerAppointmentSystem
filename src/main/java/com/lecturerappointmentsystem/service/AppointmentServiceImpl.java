package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.Appointments;
import com.lecturerappointmentsystem.repo.AppointmentRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class AppointmentServiceImpl extends BaseServiceImpl<Appointments, Appointments, Appointments> implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        super(appointmentRepository);
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    protected Class<Appointments> getEntityClass() {
        return Appointments.class;
    }

    @Override
    public Appointments create(Appointments request) {

        val detailsExists = appointmentRepository.existsById(request.getId());

        if (detailsExists) {
            throw new InvalidRequestException("Appointment with the same name already exists");
        }

        val appointment = Appointments.fromCommand(request);

        return appointmentRepository.save(appointment);
    }


    @Override
    public Appointments update(Appointments request) {

        val detailsExists = appointmentRepository.existsById(request.getId());

        if (!detailsExists) {
            throw new InvalidRequestException("Appointment not found");
        }

        val appointment = findById(request.getId());

        appointment.update(request);

        return appointmentRepository.save(appointment);
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