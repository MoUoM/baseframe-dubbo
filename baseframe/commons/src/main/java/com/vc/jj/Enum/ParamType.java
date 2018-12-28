package com.vc.jj.Enum;

public class ParamType {

	/**
	 * 代驾类型
	 */
	public class DrivingType{
		public static final String Daily = "1";		//日常
		public static final String Booking = "2";		//预约
		public static final String Long_distance = "3";		//长途
		public static final String OneKey = "4";		//一键下单
		public static final String Generation_calling = "5";			//代叫
	}

	/**
	 * 支付方式
	 */
	public class PayMethod{
		public static final String WeixinPay = "weixinpay";
		public static final String AliPay = "alipay";
	}

	/**
	 * 开票状态
	 */
	public class InvoiceStatus{
		public static final String UnInvoice = "1";		//未开发票
		public static final String Invoice = "2";		//已开发票
	}
	
}
