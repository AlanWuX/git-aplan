package com.aplan;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class MangoBackupApplicationTests {
	public static void main(String[] args) throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Adobe\\Acrobat 11.0\\Acrobat\\Acrobat.exe");
		if(process.waitFor() == 0) {
			System.out.println("已经启动");
		}
	}


}
