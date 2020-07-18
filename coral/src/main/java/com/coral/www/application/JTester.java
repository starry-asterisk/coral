/* 
 * JTester.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www.application;
 
/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
public class JTester {
	/* 자바 실행기 */
	
	/** 파일 작성기 */
	JFileWriter fileWriter;
	
	/** 파일 이름 */
	String fileName;
	
	/** 명령프롬포트 */
	Cmd cmd;
	
	/** 기본 생성자 */
	public JTester() {
		super();
		fileWriter = new JFileWriter();
		cmd = new Cmd();
	}
	
	/** 파일이름, 내용을 지정하는 생성자 */
	public JTester(String fileName, String txt) {
		fileWriter = new JFileWriter(fileName+".java",txt,"EUC-KR");
		this.fileName = fileName;
		cmd = new Cmd();
	}
	
	/**
	 * 파일 작성
	 * 
	 * @param fileName
	 * @param txt
	 * @return
	 */
    public boolean mkJFile(String fileName, String txt) {
    	return fileWriter.mkFile(fileName+".java", txt);
    }
    
    /**
     * 프로그램 컴파일링
     * 
     * @return
     */
    public String javac() {
    	return cmd.exec("javac "+fileName+".java");
    }
    
    /**
     * 프로그램 실행
     * 
     * @return
     */
    public String java() {
    	return cmd.exec("java -Djava.security.manager "+fileName);
    }
    
    /**
     * 파일 삭제, 종료
     * 
     * @return
     */
    public String close() {
    	return cmd.exec("del "+fileName+".java")+cmd.exec("del "+fileName+".class");
    }
    
    /**
     * 파일명 지정해서 삭제, 종료
     * 
     * @return
     */
    public String close(String fileName) {
    	return cmd.exec("del "+fileName+".java")+cmd.exec("del "+fileName+".class");
    }
}
   
