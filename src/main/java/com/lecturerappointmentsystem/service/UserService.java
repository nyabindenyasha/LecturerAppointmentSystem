package com.lecturerappointmentsystem.service;

import com.lecturerappointmentsystem.model.Account;

public interface UserService extends BaseService<Account, Account, Account> {

    Account login(String username, String pasword);
    Account updatePassword(String newPassword, String oldPassword);
    String logout();

    //You can think of other functionalities we need on accounts management. Good nyt man.
}
