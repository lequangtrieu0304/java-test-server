package com.springboot.ecommerce.services;

import com.springboot.ecommerce.exceptions.UserException;
import com.springboot.ecommerce.models.User;

public interface UserService {
    User getMe() throws UserException;
}
