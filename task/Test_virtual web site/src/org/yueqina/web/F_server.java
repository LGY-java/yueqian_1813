package org.yueqina.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Principal;

import javax.print.attribute.standard.PageRanges;

public class F_server {

	public static void main(String[] args) throws IOException {
		File file = new File("imag");
		file.mkdir();
		ServerSocket server = new ServerSocket(10022);
		while (true) {
			Socket accept = server.accept();
				new Thread(){
					@Override
					public void run() {
						try {
							InputStream is = accept.getInputStream();
							BufferedReader Reade = new BufferedReader(new InputStreamReader(accept.getInputStream()));
							PrintStream printS = new PrintStream(accept.getOutputStream());
							String fileName=Reade.readLine();
							new File(file,fileName);
							
							if(file.exists()){
								printS.println("存在");
								accept.close();
								return;
							}else {
								printS.println("不存在");
							}
							FileOutputStream fos = new FileOutputStream(file);
							byte[] arr = new byte[8192];
							int len;
							
							while((len = is.read(arr)) != -1) {
								fos.write(arr, 0, len);
							}
							
							fos.close();
							accept.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}.start();
			
		}
		
	}

}
