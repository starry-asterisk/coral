package com.coral.www.File;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	
	public FileDTO restore(String filename, byte[] file);
	public String genSaveFileName(String extName);
	public boolean writeToFile(String filename, byte[] file);
	public boolean delFile(String fileName);
	public boolean insert(String[] files, String[] filesType, String[] filesName, String bno);
	public List<FileDTO> getAttachment(String no);
	public String newProfImg(MultipartFile file, String id);
	public String newProfImg(String url, String id);
	public boolean update(String[] files, String[] filesType, String[] filesName, String bno);
}