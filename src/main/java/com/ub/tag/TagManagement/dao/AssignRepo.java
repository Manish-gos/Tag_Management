package com.ub.tag.TagManagement.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ub.tag.TagManagement.Model.Assign;

@Repository
@Service
public interface AssignRepo extends JpaRepository<Assign, Long> {
	
	List<Assign> findByUserNameOrderByTagIdDesc(String userName);

	Assign findByTagId(Long id);

	/*
	 * @Transactional
	 * 
	 * @Modifying(clearAutomatically = true)
	 * 
	 * @Query("update assign a set a.status =:status, a.image =:image ,a.description =:description, a.closeDate =:closedate where a.tagId =:tagid"
	 * ) public void findByDescriptionOrderById(@Param(value = "status") String
	 * status, @Param(value = "image") String image,
	 * 
	 * @Param(value = "description") String description,
	 * 
	 * @Param(value = "closedate") String clodeDate, @Param(value = "tagid") Long
	 * tagid);
	 */
	void deleteByTagId(Long id);
}