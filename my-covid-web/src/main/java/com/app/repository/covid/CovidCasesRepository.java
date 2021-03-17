package com.app.repository.covid;

import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;

public interface CovidCasesRepository extends JpaRepository<CovidCasesAreaEntity, UUID>  {

	@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 2", nativeQuery = true)
	List<CovidCasesAreaEntity> listLast2Records();
	
	//Two lines are commented because it is only useful for database using pgadmin
	//@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 5", nativeQuery = true)
	//List<CovidCasesAreaEntity> listLast5Records();
	
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")	
	List<CovidCasesAreaEntity> listLast5RecordsHQLWithSize(org.springframework.data.domain.Pageable page);
	//List<CovidCasesAreaEntity> listLast5RecordsHQL();//this line is used for bonus practical part 2
	
	
}