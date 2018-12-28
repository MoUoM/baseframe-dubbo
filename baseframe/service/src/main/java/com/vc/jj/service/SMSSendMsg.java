package com.vc.jj.service;

public class SMSSendMsg {
    static String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
    static String account = "lyp086";//示远账号
    static String pswd = "Sy123456";//示远密码

    public  static boolean sendMsg(String mobiles,String validateCode,String content){

      //  String mobiles = "13922329650";//手机号码，多个号码使用","分割
        //String content = "您好，您的验证码是："+validateCode;//短信内容，注意内容中的逗号请使用中文状态下的逗号
        boolean needstatus = true;//是否需要状态报告，需要true，不需要false
        String product = "";//产品ID(不用填写)
        String extno = "";//扩展码(不用填写)
        try {
            String returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
            System.out.println(returnString);
            //TODO 处理返回值,参见HTTP协议文档
            return  true;
        } catch (Exception e) {
            //TODO 处理异常
            e.printStackTrace();
            return  false;
        }
    }




    public static void main(String args[]) throws Exception{
        SMSSendMsg.sendMsg("13922329650","","验证码:123456,您正在重置密码,如果不是本人操作请忽略.");
    }
}
