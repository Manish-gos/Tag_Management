package com.ub.tag.TagManagement.dao;
import com.ub.tag.TagManagement.Model.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}