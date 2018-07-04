package com.example.demo.Service;

import com.example.demo.Domain.AccountCode;
import com.example.demo.Domain.Request.UserRequest;
import com.example.demo.Domain.User;
import com.example.demo.Repository.AccountCodeRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Interface.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountCodeRepository accountCodeRepository;

    @Override
    public User findUserByEmailAddressAndPassword(String emailAddress, String password) {
        return userRepository.findByEmailAddressAndPassword(emailAddress, password);
    }

    @Override
    public User addNewUser(String lastName, String firstName,
                           String emailAddress, String password, String phoneNumber) {
        User newUser = new User(lastName, firstName, emailAddress, password, phoneNumber);
        return userRepository.save(newUser);
    }

    @Override
    public User findUserByEmailAddress(String email){
        return userRepository.findByEmailAddress(email);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.getAll();
        for(User user : users){
            System.out.println(user.getFirstName() + " - "+ user.getAccountStatus());
        }
        return users;
    }

    @Override
    public List<User> getAllUsersWithConfirmatedAccount() {
        List<User> all_users = userRepository.getAll();
        List<User> users =  new ArrayList<>();
        for(User user : all_users){
            if(user.getAccountStatus() == 1)
              users.add(user);
        }
        return users;
    }

    @Override
    public AccountCode addNewAccount(UserRequest user) {
        User new_user = addNewUser(user.getLastName(), user.getFirstName(),
                user.getEmailAddress(), user.getPassword(), user.getPhoneNumber());
        AccountCode account = new AccountCode();
       if(new_user!= null) {
           account = new AccountCode(new_user, user.getCode());
           account =  accountCodeRepository.save(account);
       }
       return account;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmailAddress(email);
    }

    @Override
    public AccountCode getAccountByUserEmail(String email){
        User user = userRepository.findByEmailAddress(email);
        if(user != null){
            return accountCodeRepository.findByUser(user);
        }
        return new AccountCode();
    }

    @Override
    public User confirmationAccount(String email, String code) {
        User new_user = new User();
        String current_code = "";
        AccountCode accountCode = getAccountByUserEmail(email);
        System.out.println(accountCode.getCode());
        if(accountCode != null) {
            current_code = accountCode.getCode();
                if (code.equals(current_code)) {
                    //date valide
                    new_user = userRepository.findByEmailAddress(email);
                    new_user.setAccountStatus(1);
                    new_user = userRepository.save(new_user);
                    accountCodeRepository.delete(accountCode);
                    return new_user;
                }
        }
        return new_user;
    }

}
