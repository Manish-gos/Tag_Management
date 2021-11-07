package com.ub.tag.TagManagement.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ub.tag.TagManagement.Model.User;
import com.ub.tag.TagManagement.user.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}