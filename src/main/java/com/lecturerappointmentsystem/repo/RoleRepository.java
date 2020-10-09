package com.lecturerappointmentsystem.repo;

import com.lecturerappointmentsystem.model.Roles;

public interface RoleRepository extends BaseRepository<Roles> {

    boolean existsByName(String name);

}
