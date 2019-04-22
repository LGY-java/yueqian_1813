package org.yueqina.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.text.LayeredHighlighter;

public class K_Client {
	public static void main(String[] args) throws Exception{
		File fi=getfile();
		Socket socket = new	Socket("127.0.0.1",10022);
		BufferedReader Reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream printS = new PrintStream(socket.getOutputStream());
		printS.println(fi.getName());
		String st =Reader.readLine();
		if("存在".equals(st)){
			System.out.println("你好，你的文件已经存在");
			socket.close();
			return;
		}
		FileInputStream fileImageIS = new FileInputStream(fi);
		byte [] arr=new byte[8192];
		int len;
		while ((len=fileImageIS.read(arr))!=-1) {
			printS.write(arr,0,len);
		}
		fileImageIS.close();
		socket.close();
		
	}
	private static File  getfile() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入文件的路径");
		while (true) {
			String lj =scanner.nextLine();
			File file = new File(lj);
			if(!file.exists()){
				System.out.println("你输入的文件路径不存在");
			}else if(!file.isDirectory()) {
				System.err.println("你输入的是一个文件夹，请输入文件路径");
			}else {
				return file;
			}	
		}
	}
}
