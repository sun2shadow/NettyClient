package com.baoshu.transprocess;

public class ClientProcess {

	public static void main(String[] args) {
		try {
			String msg = "<Function>JY</Function><Flag>B</Flag><ChildFundid>T09</ChildFundid><StkCode>600000</StkCode><StkName>ÆÖ·¢ÒøÐÐ</StkName><Qty>100</Qty><Price>12.68</Price><OrderType /><Time>20171124093738</Time>";
			new TransProcessClient(msg).run();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
