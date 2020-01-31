/* 
 * JFileWriter.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
public class JFileWriter {
	/* java 파일 관리자 */
	
	/** 기본 인코딩 타입 */
	private String ENC_TYPE = "UTF-8";
	
	/**
	 * 기본 생성자
	 */
	public JFileWriter() {
		super();
	}

	/**
	 * 파일생성을 하는 생성자
	 * 
	 * @param fileName
	 * @param txt
	 */
	public JFileWriter(String fileName, String txt) {
		mkFile(fileName, txt, null);
	}
	
	/**
	 * 파일생성+인코딩 지정 하는 생성자
	 * 
	 * @param fileName
	 * @param txt
	 * @param ENC_TYPE
	 */
	public JFileWriter(String fileName, String txt, String ENC_TYPE) {
		mkFile(fileName, txt, ENC_TYPE);
	}
	
	/**
	 * 인코딩 미지정 파일 생성
	 * 
	 * @param fileName
	 * @param txt
	 * @return
	 */
	public boolean mkFile(String fileName, String txt) {
		return mkFile(fileName, txt, null);
	}
	
	/**
	 * 인코딩 지정 파일 생성
	 * 
	 * @param fileName
	 * @param txt
	 * @param ENC_TYPE
	 * @return
	 */
	public boolean mkFile(String fileName, String txt, String ENC_TYPE) {
		if(ENC_TYPE!=null) {
			 this.ENC_TYPE = ENC_TYPE;
		}
		fileName = "C:\\coding\\" + fileName;

		try {

			// BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
			BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), this.ENC_TYPE));

			// 파일안에 문자열 쓰기
			fw.write(txt);
			fw.flush();

			// 객체 닫기
			fw.close();

			return true;
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * 파일 읽어 오기
	 * 
	 * @param fileName
	 * @return
	 */
	public String readFile(String fileName) {
        fileName = "C:\\coding\\"+fileName;
         
         
        try{
                         
            // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), this.ENC_TYPE));
            
            String txt="";
            String line = br.readLine();
            while(line!=null) {
            	txt += line;
            	line = br.readLine();
            }
            
            
            // 파일안에 문자열 쓰기
            
 
            // 객체 닫기
            br.close();
            
            return txt; 
        }catch(Exception e){
            //e.printStackTrace();
            return null; 
        }
    }

	/**
	 * 파일 삭제
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean delFile(String fileName){ 
		File file = new File("C:\\coding\\"+fileName);
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
}
