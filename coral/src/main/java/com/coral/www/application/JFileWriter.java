package com.coral.www.application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
 
 
public class JFileWriter {
	public JFileWriter() {
		super();
	}
	
	public JFileWriter(String fileName, String txt) {
		mkFile(fileName,txt);
	}
     
     
    public boolean mkFile(String fileName, String txt) {
        fileName = "C:\\coding\\"+fileName;
         
         
        try{
                         
            // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
             
            // 파일안에 문자열 쓰기
            fw.write(txt);
            fw.flush();
 
            // 객체 닫기
            fw.close();
            
            return true; 
        }catch(Exception e){
            //e.printStackTrace();
            return false; 
        }
    }
}
   
