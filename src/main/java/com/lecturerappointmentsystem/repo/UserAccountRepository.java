package com.lecturerappointmentsystem.repo;

import com.lecturerappointmentsystem.model.Account;

public interface UserAccountRepository extends BaseRepository<Account> {

    boolean existsByUsername(String name);

}
