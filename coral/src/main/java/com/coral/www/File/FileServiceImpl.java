package com.coral.www.File;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{
	// 리눅스 기준으로 파일 경로를 작성 ( 루트 경로인 /으로 시작한다. )
	// 윈도우라면 workspace의 드라이브를 파악하여 JVM이 알아서 처리해준다.
	// 따라서 workspace가 C드라이브에 있다면 C드라이브에 upload 폴더를 생성해 놓아야 한다.
	private static final String SAVE_PATH = "C:/coding/upload";
	private static final String PREFIX_URL = "/upload/";
	
	@Inject
	FileDAO dao;
	
	@Override
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
	@Override
	public String genSaveFileName(String extName) {
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
	
	@Override
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
	
	@Override
	public boolean delFile(String fileName){ 
		File file = new File(SAVE_PATH+"/"+fileName);
		boolean result = false;
		try {
			if( file.exists() ){ 
				if(file.delete()){ 
					result = true;
				}
			}
		}catch(Exception e) {}
		return result;
	}
	
	@Override
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

	@Override
	public List<FileDTO> getAttachment(String no) {
		return dao.getList(no);
	}

	@Override
	public String newProfImg(MultipartFile file, String id) {
		String result = null;
		System.out.println("User ID       : "+id);
		System.out.println("Name          : "+file.getName());
		System.out.println("Size          : "+file.getSize());
		System.out.println("Original Name : "+file.getOriginalFilename());
		try {
			FileDTO dto = new FileDTO();
			String pattern = "^\\S+.(?i)(exe)$";
			if(!Pattern.matches(pattern,file.getOriginalFilename().replace(" ","_").toLowerCase())) {
				dto = restore(file.getOriginalFilename(),file.getBytes());
				dto.setBno(id);
				dto.setOrder(0);
			}
			if(getAttachment(id).size()<1) {
				result = dao.insert(dto)?dto.getPath():null;
			}else {
				for(FileDTO files :getAttachment(id)) {
					delFile(files.getKeyname());
				}
				result = dao.update(dto)?dto.getPath():null;
			}
		}catch(Exception e) {
			e.printStackTrace(System.out);
			//실패시 파일 삭제 구현해 주세요
		}
		
		return result;
	}
	
	@Override
	public String newProfImg(String url, String id) {
		String result = null;
		try {
			FileDTO dto = new FileDTO();
			dto.setBno(id);
			dto.setKeyname(genSaveFileName(""));
			dto.setName("profile");
			dto.setOrder(0);
			dto.setPath(url);
			dto.setSize("0");
			if(getAttachment(id).size()<1) {
				result = dao.insert(dto)?dto.getPath():null;
			}else {
				for(FileDTO files :getAttachment(id)) {
					delFile(files.getKeyname());
				}
				result = dao.update(dto)?dto.getPath():null;
			}
		}catch(Exception e) {
			e.printStackTrace(System.out);
			//실패시 파일 삭제 구현해 주세요
		}
		
		return result;
	}

	@Override
	public boolean update(String[] files, String[] filesType, String[] filesName, String bno) {
		boolean result = true;
		if(bno==null){
			result = false;
		}else if(files!=null&&filesName.length==filesType.length&&filesType.length==files.length) {
			try {
				int count = 0;
				FileDTO dto;
				List<FileDTO> list = getAttachment(bno);
				String pattern = "^\\S+.(?i)(exe)$";
				for(String file:files) {
					if(filesType[count].equals("false")) {
						delFile("/"+list.get(count).getKeyname());
						dao.delete(list.get(count));
					}else if(!filesType[count].equals("true")){
						if(!Pattern.matches(pattern,filesType[count].replace(" ","_").toLowerCase())) {
							dto = restore(filesName[count],Base64.getMimeDecoder().decode(file));
							dto.setBno(bno);
							dto.setOrder(dao.newOrder(bno));
							result = dao.insert(dto)&&result;
						}else {
							count--;
						}
					}
					count++;
				}
			}catch(Exception e) {
				e.printStackTrace(System.out);
				result = false;
				//실패시 파일 삭제 구현해 주세요
			}
		}else {
			for(FileDTO dto:getAttachment(bno)) {
				delFile(dto.getKeyname());
				dao.delete(dto);
			}
		}
		return result;
	}
}