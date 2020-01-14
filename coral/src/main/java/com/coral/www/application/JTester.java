package com.coral.www.application;
 
 
public class JTester {
	JFileWriter fileWriter;
	String fileName;
	Cmd cmd;
	public JTester() {
		super();
		fileWriter = new JFileWriter();
		cmd = new Cmd();
	}
	public JTester(String fileName, String txt) {
		fileWriter = new JFileWriter(fileName+".java",txt,"EUC-KR");
		this.fileName = fileName;
		cmd = new Cmd();
	}
    public boolean mkJFile(String fileName, String txt) {
    	return fileWriter.mkFile(fileName+".java", txt);
    }
    public String javac() {
    	return cmd.exec("javac "+fileName+".java");
    }
    public String java() {
    	return cmd.exec("java -Djava.security.manager "+fileName);
    }
    public String close() {
    	return cmd.exec("del "+fileName+".java")+cmd.exec("del "+fileName+".class");
    }
    public String close(String fileName) {
    	return cmd.exec("del "+fileName+".java")+cmd.exec("del "+fileName+".class");
    }
}
   
