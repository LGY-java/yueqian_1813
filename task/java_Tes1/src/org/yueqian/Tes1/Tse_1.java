package org.yueqian.Tes1;

public class Tse_1 {
	public static void main (String []args) throws InterruptedException{
		xues xues = new xues();
		sud sud = new sud();
		Thread iThread =new Thread(){

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
						sud.sudlt(xues.xusr());
					}
			}
			
		};
		iThread.start();
		iThread.join(100);
		//Thread.sleep(1000);
		
		new Thread(){
			@Override
			public void run() {
				for (int i = 0; i <10; i++) {
						sud.sudlt(xues.xusr1());
					}
			}
			
		}.start();
	}
}

class sud{
	public void sudlt(String ss) {
		System.out.println(ss+"上厕所");
	}
}

class xues{
	public String xues;
	public int  i=1;
	public String  xusr(){
		synchronized (this) {
			return "张三";
		}
	}
	public String xusr1(){
		synchronized(this){
			return "李四";
		}
		
	
		}
	
}