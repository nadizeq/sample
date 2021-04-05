package com.app.repository.covid;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesBonusEntity;

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
}
