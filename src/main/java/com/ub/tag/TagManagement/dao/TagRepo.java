package com.ub.tag.TagManagement.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ub.tag.TagManagement.Model.Tag;

@Repository
@Service
public interface TagRepo extends JpaRepository<Tag, Long>{

	List<Tag> findByUserNameOrderByIdDesc(String userName);

	List<Tag> findByAreaOrderByIdDesc(String area);

	List<Tag> findByDepartmentOrderByIdDesc(String department);
	
	
}