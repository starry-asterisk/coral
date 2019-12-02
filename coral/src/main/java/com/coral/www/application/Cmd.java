package com.coral.www.application;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cmd{
	
	private static final Logger logger = LoggerFactory.getLogger(Cmd.class);
	
	public Cmd(){
		super();
	}

	public Cmd(String command) {
		exec(command);
    }
	
	public String exec(String command) {
		StringBuffer stringBuffer = new StringBuffer();
		ProcessBuilder processBuilder = new ProcessBuilder();

        // Run this on Windows, cmd, /c = terminate after this run
        processBuilder.command("cmd.exe", "/c", "C: && cd coding  &&"+command);
        

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