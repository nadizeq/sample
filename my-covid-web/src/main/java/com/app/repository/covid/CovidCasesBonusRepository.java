package com.app.repository.covid;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesBonusEntity;

//TODO: Practical bonus final
// complete this as JpaRepository

// hint: interface is similar to CovidCasesDescRepository
public interface CovidCasesBonusRepository extends JpaRepository<CovidCasesBonusEntity, Long>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CovidCasesBonusEntity d WHERE d.description = :desc")
	void deleteBonusDescWithCondition(String desc);

	@Transactional
	@Modifying
	@Query("SELECT description FROM CovidCasesBonusEntity d GROUP BY description HAVING COUNT(*)>1")
	List<String> findDuplicateNdelete(); 
	
	//find query to find duplicate values
	/*
	@Query("DELETE FROM CovidCasesBonusEntity d WHERE d.description = :desc")
	List<String> findDuplicate(); 
	 */
	
	//@Query(DELETE d1 FROM CovidCasesDescEntity d1 INNERJOIN CovidCasesDescEntity d1 WHERE d2.description=d2.description)
	//@Query("DELETE FROM (SELECT *,  ROW_NUMBER() OVER (PARTITION BY description ORDER BY description) AS rn  FROM  CovidCasesDescEntity) desc  WHERE  rn> 1")
	//@Query(SELECT id, description, ROW_NUMBER()OVER (PARTITION BY description ORDER BY description) AS row_num FROM CovidCasesDescEntity; )
}
