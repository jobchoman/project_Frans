package com.frans.schedule.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.frans.schedule.dto.FacilitiesDTO;

@Mapper
public interface FacilitiesDAO {

	ArrayList<FacilitiesDTO> facilitiesList();

	void facilitiesDelete(String fac_idx);

	void facilitiesWrite(FacilitiesDTO facilitiesDTO);

	FacilitiesDTO facilitiesDetail(String fac_idx);

	void facilitiesUdapte(FacilitiesDTO facilitiesDTO);

	void rentWrite(FacilitiesDTO facilitiesDTO);

	

	
	
}
