/* 
 * FileService.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.File;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Service
public interface FileService {
	/* 파일 서비스 인터페이스  */
	
	/**
	 * 단일 파일 생성 관리
	 * 
	 * @param filename
	 * @param file
	 * @return
	 */
	public FileDTO restore(String filename, byte[] file);

	/**
	 * 단일 차일 저장
	 * 
	 * @param filename
	 * @param file
	 * @return
	 */
	public boolean writeToFile(String filename, byte[] file);
	
	/**
	 * 파일 이름 생성
	 * 
	 * @param extName
	 * @return
	 */
	public String genSaveFileName(String extName);
	
	/**
	 * 파일 삭제
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean delFile(String fileName);
	
	/**
	 * 다중 파일 생성 
	 * 
	 * @param files
	 * @param filesType
	 * @param filesName
	 * @param bno
	 * @return
	 */
	public boolean insert(String[] files, String[] filesType, String[] filesName, String bno);
	
	/**
	 * 파일 목록 불러오기
	 * 
	 * @param no
	 * @return
	 */
	public List<FileDTO> getAttachment(String no);
	
	/**
	 * 프로필 이미지 by blub
	 * 
	 * @param file
	 * @param id
	 * @return
	 */
	public String newProfImg(MultipartFile file, String id);
	
	/**
	 * 프로필 이미지 by URL
	 * 
	 * @param url
	 * @param id
	 * @return
	 */
	public String newProfImg(String url, String id);
	
	/**
	 * 첨부파일 수정
	 * 
	 * @param files
	 * @param filesType
	 * @param filesName
	 * @param bno
	 * @return
	 */
	public boolean update(String[] files, String[] filesType, String[] filesName, String bno);
}