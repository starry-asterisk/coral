/* 
 * Cmd.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
public class Cmd{
	/* 명령프롬프트 사용기능을 제공합니다 */
	
	/** 기록제공 객체 */
	private static final Logger logger = LoggerFactory.getLogger(Cmd.class);
	
	/** 기본 생성자 */
	public Cmd(){
		super();
	}

	/** 실행과 동시에 명령어를 수행하는 생성자 */
	public Cmd(String command) {
		exec(command);
    }
	
	/**
	 * 명령어 실행 메소드
	 * 
	 * @param command
	 * @return 실행 결과
	 */
	public String exec(String command) {
		StringBuffer stringBuffer = new StringBuffer();
		ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command("cmd.exe", "/c", "C: && cd /coding  &&"+command);
        

        try {

            Process process = processBuilder.start();

			// blocked :(
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream(),"EUC-KR"));

            String line;
            while ((line = reader.readLine()) != null) {
            	stringBuffer.append(line+"\n");
            }

            int exitCode = process.waitFor();
            logger.info("Exited with error code : " + exitCode);
            if(exitCode!=0) {
            	reader =
                        new BufferedReader(new InputStreamReader(process.getErrorStream(),"EUC-KR"));
            	while ((line = reader.readLine()) != null) {
                    stringBuffer.append(line+"\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
	}

}