package com.frans.sign.dto;

import org.apache.ibatis.type.Alias;

@Alias("fileDTO")
public class fileDTO {
	
	private String file_idx;
	private String file_sort_idx;
	private String file_from;
	private String file_ori;
	private String file_new;
	
	public String getFile_idx() {
		return file_idx;
	}
	public void setFile_idx(String file_idx) {
		this.file_idx = file_idx;
	}
	public String getFile_sort_idx() {
		return file_sort_idx;
	}
	public void setFile_sort_idx(String file_sort_idx) {
		this.file_sort_idx = file_sort_idx;
	}
	public String getFile_from() {
		return file_from;
	}
	public void setFile_from(String file_from) {
		this.file_from = file_from;
	}
	public String getFile_ori() {
		return file_ori;
	}
	public void setFile_ori(String file_ori) {
		this.file_ori = file_ori;
	}
	public String getFile_new() {
		return file_new;
	}
	public void setFile_new(String file_new) {
		this.file_new = file_new;
	}
	

}
