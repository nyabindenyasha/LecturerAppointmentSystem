package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.local.requests.RoleCreateRequest;
import com.lecturerappointmentsystem.model.Roles;
import com.lecturerappointmentsystem.repo.RoleRepository;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
class RoleServiceImpl extends BaseServiceImpl<Roles, RoleCreateRequest, Roles> implements RoleService {

    private final RoleRepository roleRepository;

    RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    protected Class<Roles> getEntityClass() {
        return Roles.class;
    }

    @Override
    public Roles create(RoleCreateRequest request) {

        val detailsExists = roleRepository.existsByName(request.getName());

        if (detailsExists) {
            throw new InvalidRequestException("Role with the same name already exists");
        }

        val role = Roles.fromCommand(request);

        return roleRepository.save(role);
    }


    @Override
    public Roles update(Roles request) {

        val detailsExists = roleRepository.existsByName(request.getName());

        if (!detailsExists) {
            throw new InvalidRequestException("Role not found");
        }

        val role = findById(request.getId());

        role.update(request);

        return roleRepository.save(role);
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
