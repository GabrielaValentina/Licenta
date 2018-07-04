package com.example.demo.Service.Interface;

import com.example.demo.Domain.AccountCode;
import com.example.demo.Domain.Request.UserRequest;
import com.example.demo.Domain.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserService {
     User findUserByEmailAddressAndPassword(String emailAddress, String password);
     User addNewUser(String lastName, String firstName, String emailAddress, String password, String phoneNumber);
     User findUserByEmailAddress(String email);
     List<User> getAll();
     AccountCode addNewAccount(UserRequest user);
     User findUserByEmail(String email);
     AccountCode getAccountByUserEmail(String email);
     User confirmationAccount(String email, String code);
      List<User> getAllUsersWithConfirmatedAccount();
}
