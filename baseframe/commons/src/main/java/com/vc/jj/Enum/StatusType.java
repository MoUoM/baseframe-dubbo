package com.vc.jj.Enum;

public class StatusType {

	/**
	 * 司机状态
	 */
	public class DriverStatus{
		public static final String Unaudited = "0";
		public static final String Audited = "1";
		public static final String NotThrough = "2";
		public static final String Prohibition = "3";
		public static final String Frozen = "4";
	}
	
	/**
	 * 用户认证状态
	 */
	public class UserCertStatus{
		public static final String NotCert = "0";		//未认证
		public static final String Cert = "1";			//已认证
	}

	/**
	 * 订单状态
	 * @author mo
	 * 1、新建订单；2、已结束未支付；3、已完成并支付; 4､司机已接单; 5､司机已到达指定地点; 6､乘客已上车､进行中; 7订单已取消
	 */
	public class OrderStatus{
		public static final String Created = "1";
		public static final String EndNotPay = "2";
		public static final String Finished = "3";
		public static final String Order = "4";
		public static final String Arrival = "5";
		public static final String Boarding = "6";
		public static final String Cancel = "7";
	}
	
	/**
	 * 优惠券状态
	 */
	public class CouponStatus{
		public static final String NotUsed = "1";		//未使用
		public static final String AlreadyUsed = "2";			//已使用
	}
}
