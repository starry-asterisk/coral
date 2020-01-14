package com.coral.www.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JFileWriter {
	private String ENC_TYPE = "UTF-8";
	public JFileWriter() {
		super();
	}

	public JFileWriter(String fileName, String txt) {
		mkFile(fileName, txt, null);
	}
	public JFileWriter(String fileName, String txt, String ENC_TYPE) {
		mkFile(fileName, txt, ENC_TYPE);
	}
	public boolean mkFile(String fileName, String txt) {
		return mkFile(fileName, txt, null);
	}
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
