package com.lecturerappointmentsystem.web;

import com.lecturerappointmentsystem.local.exceptions.InvalidRequestException;
import com.lecturerappointmentsystem.model.Account;
import com.lecturerappointmentsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = "UserAccounts")
@RequestMapping("v1/userAccounts")
public class UserAccountController {

    private final UserService userAccountService;

    public UserAccountController(UserService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("")
    @ApiOperation("Get UserAccounts")
    public Page<Account> getAll(@PageableDefault(sort = "name") Pageable pageable,
                                @RequestParam(required = false) String search) {
        return userAccountService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get All UserAccounts")
    public Collection<Account> getAll() {
        return userAccountService.findAll();
    }

    @GetMapping("/{userAccountId}")
    @ApiOperation("Get a UserAccount by Id")
    public Account getUserAccount(@PathVariable long userAccountId) {
        return userAccountService.findById(userAccountId);
    }

    @DeleteMapping("/{userAccountId}")
    @ApiOperation("Delete a UserAccount by Id")
    public void delete(@PathVariable long userAccountId) {
        userAccountService.delete(userAccountId);
    }


    @PostMapping("")
    @ApiOperation("Create UserAccount")
    public Account create(@RequestBody Account request) {
        return userAccountService.create(request);
    }

    @PutMapping("/{currencyId}")
    @ApiOperation("Update Currency")
    public Account update(@RequestBody Account request, @PathVariable long userAccountId) {
        if(request.getId() != userAccountId){
            throw new InvalidRequestException("You can not delete this record as it might be used by another record");
        }
        return userAccountService.update(request);
    }
}
