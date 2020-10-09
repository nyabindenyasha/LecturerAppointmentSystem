package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.Account;
import com.lecturerappointmentsystem.repo.UserAccountRepository;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;


@Service
class UserAccountServiceImpl extends BaseServiceImpl<Account, Account, Account> implements UserService {

    private final UserAccountRepository userAccountRepository;

    UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        super(userAccountRepository);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Account create(Account request) {

        val detailsExists = userAccountRepository.existsByUsername(request.getUsername());

        if (detailsExists) {
            throw new InvalidRequestException("UserAccount with the same name already exists");
        }

        val userAccount = Account.fromCommand(request);

        return userAccountRepository.save(userAccount);
    }


    @Override
    public Account update(Account request) {

        val detailsExists = userAccountRepository.existsByUsername(request.getUsername());

        if (!detailsExists) {
            throw new InvalidRequestException("UserAccount not found");
        }

        val userAccount = findById(request.getId());

        userAccount.update(request);

        return userAccountRepository.save(userAccount);
    }

    @Override
    public void delete(Long id) {
        try {
            super.delete(id);
        } catch (ConstraintViolationException var3) {
            throw new InvalidRequestException("You can not delete this record is might be used by another record");
        }
    }

    @Override
    public Account login(String username, String pasword) {
        return null;
    }

    @Override
    public Account updatePassword(String newPassword, String oldPassword) {
        return null;
    }

    @Override
    public String logout() {
        return null;
    }
}



