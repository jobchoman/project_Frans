package com.frans.sign.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.frans.sign.dto.DocFormDTO;

@Mapper
public interface DocFormDAO {

	ArrayList<DocFormDTO> docFormList(String doc_type, String lineup);

	ArrayList<DocFormDTO> docFormSearch(String keyword);

	int docFormWrite(DocFormDTO docFormDTO);

	DocFormDTO docFormDetail(String doc_form_idx);

	int docFormDelete(String doc_form_idx);
	
	void docForm_hit_update(String doc_form_idx);

}
