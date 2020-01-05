package com.coral.www.File;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class FileService {
	// 리눅스 기준으로 파일 경로를 작성 ( 루트 경로인 /으로 시작한다. )
	// 윈도우라면 workspace의 드라이브를 파악하여 JVM이 알아서 처리해준다.
	// 따라서 workspace가 C드라이브에 있다면 C드라이브에 upload 폴더를 생성해 놓아야 한다.
	private static final String SAVE_PATH = "C:/coding/upload";
	private static final String PREFIX_URL = "/upload/";
	
	@Inject
	FileDAO dao;
	
	public FileDTO restore(String filename, byte[] file) {
		FileDTO dto = null;
		
		try {
			// 파일 정보
			Long size = (long) file.length;
			String extName="";
			if(filename.lastIndexOf(".")!=-1) {
				extName = filename.substring(filename.lastIndexOf("."), filename.length());
			}
			
			String saveFileName = genSaveFileName(extName);
			
			// 서버에서 저장 할 파일 이름
			
			
			if(writeToFile(saveFileName, file)) {
				dto = new FileDTO();
				dto.setKeyname(saveFileName);
				if(filename.getBytes().length>100) {
					filename = "ToolongNameFile"+extName;
				}
				dto.setName(filename);
				dto.setPath(PREFIX_URL + saveFileName);
				dto.setSize(size.toString());
			}
		}catch(Exception e) {}
		return dto;
	}
	
	
	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += "_"+UUID.randomUUID().toString();
		fileName += extName;
		
		return fileName;
	}
	
	public boolean writeToFile(String filename, byte[] file){
		boolean result = false;
	    if(file != null){
	    	try{
		        FileOutputStream lFileOutputStream = new FileOutputStream(SAVE_PATH + "/" + filename);
		        lFileOutputStream.write(file);
		        lFileOutputStream.close();
		        result = true;
		    }catch(Throwable e){
		        e.printStackTrace(System.out);
		    }
	    }
	    return result;
	}
	
	public boolean insert(String[] files, String[] filesType, String[] filesName, String bno){
		boolean result = true;
		if(bno==null){
			result = false;
		}else if(files!=null&&filesName.length==filesType.length&&filesType.length==files.length) {
			try {
				int count = 0;
				List<FileDTO> list = new ArrayList<FileDTO>();
				String pattern = "^\\S+.(?i)(exe)$";
				for(String file:files) {
					if(!Pattern.matches(pattern,filesType[count].replace(" ","_").toLowerCase())) {
						list.add(restore(filesName[count],Base64.getMimeDecoder().decode(file)));
						list.get(count).setBno(bno);
						list.get(count).setOrder(count);
						result = dao.insert(list.get(count))&&result;
						count++;
					}
				}
			}catch(Exception e) {
				e.printStackTrace(System.out);
				result = false;
				//실패시 파일 삭제 구현해 주세요
			}
		}
		return result;
	}


	public List<FileDTO> getAttachment(String no) {
		return dao.getList(no);
	}
}