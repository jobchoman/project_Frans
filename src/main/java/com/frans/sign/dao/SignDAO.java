package com.frans.sign.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.dto.signDTO;

@Mapper
public interface SignDAO {

	ArrayList<signDTO> signList();

	ArrayList<signDTO> dateSearch(String startdate, String enddate);

	DocFormDTO signWriteGo(String doc_form_idx);

}
