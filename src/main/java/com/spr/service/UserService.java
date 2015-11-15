package com.spr.service;

import com.spr.exception.UserNotFound;
import com.spr.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {

	public User create(User user);
	public List<User> getByName(String name);
	public User delete(int id) throws UserNotFound;
	public User update(User user) throws UserNotFound;
	public User findById(int id);
	public Page<User> findAll(PageRequest pageRequest);
	public List<User> findAll();

}
