package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.Facaulty;
import com.lecturerappointmentsystem.repo.FacaultyRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
class FacaultyServiceImpl extends BaseServiceImpl<Facaulty, Facaulty, Facaulty> implements FacaultyService {

    private final FacaultyRepository facaultyRepository;

    FacaultyServiceImpl(FacaultyRepository facaultyRepository) {
        super(facaultyRepository);
        this.facaultyRepository = facaultyRepository;
    }

    @Override
    protected Class<Facaulty> getEntityClass() {
        return Facaulty.class;
    }

    @Override
    public Facaulty create(Facaulty request) {

        val detailsExists = facaultyRepository.existsByName(request.getName());

        if (detailsExists) {
            throw new InvalidRequestException("Facaulty with the same name already exists");
        }

        val role = Facaulty.fromCommand(request);

        return facaultyRepository.save(role);
    }


    @Override
    public Facaulty update(Facaulty request) {

        val detailsExists = facaultyRepository.existsByName(request.getName());

        if (!detailsExists) {
            throw new InvalidRequestException("Facaulty not found");
        }

        val facaulty = findById(request.getId());

        facaulty.update(request);

        return facaultyRepository.save(facaulty);
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