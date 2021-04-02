package com.app.repository.covid;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository  extends JpaRepository<CovidCasesDescEntity, Long>  {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CovidCasesDescEntity d WHERE d.description = :desc")
	void deleteDescWithCondition(String desc);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CovidCasesDescEntity d WHERE d.description = :desc")
	int deleteDescWithConditionStatus(String desc);
	
	/*
	@Transactional
	@Modifying
	@Query("SELECT id, description, ROW_NUMBER()OVER (PARTITION BY description ORDER BY description) AS row_num FROM CovidCasesDescEntity")
	List<String> findDuplicateNdelete(); 
*/
	//find query to find duplicate values
	
	//@Query(DELETE d1 FROM CovidCasesDescEntity d1 INNERJOIN CovidCasesDescEntity d1 WHERE d2.description=d2.description)
	//@Query("DELETE FROM (SELECT *,  ROW_NUMBER() OVER (PARTITION BY description ORDER BY description) AS rn  FROM  CovidCasesDescEntity) desc  WHERE  rn> 1")
	//@Query(SELECT id, description, ROW_NUMBER()OVER (PARTITION BY description ORDER BY description) AS row_num FROM CovidCasesDescEntity; )
}
