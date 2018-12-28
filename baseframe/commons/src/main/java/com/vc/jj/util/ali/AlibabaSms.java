package com.vc.jj.util.ali;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.vc.jj.Enum.ResultEnum;
import com.vc.jj.consts.AliyunSmsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author: zm
 * @createDate: 2018/7/23$ 17:42$
 * @updateRemark: 修改内容
 * @description:
 */
public class AlibabaSms {

    public static final Logger logger = LoggerFactory.getLogger(AlibabaSms.class);

    /**
     * @param toPhoneNum
     * @param validateCode
     * @return
     * @throws ClientException
     */
    public static ResultEnum sendValidateCode(String toPhoneNum, String validateCode,
                                              Integer messageType) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile
                .getProfile("cn-hangzhou", AliyunSmsConstants.ACCESSKEY_ID,
                        AliyunSmsConstants.ACCESSKEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", AliyunSmsConstants.PRODUCT,
                AliyunSmsConstants.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(toPhoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("瓶什么");

        //必填:短信模板-可在短信控制台中找到

        if (messageType == null) {
            request.setTemplateCode(AliyunSmsConstants.VALIDATE_TEMPLATE_CODE);
        } else {
            switch (messageType) {
                case AliyunSmsConstants.LOGIN_TYPE:
                    request.setTemplateCode(AliyunSmsConstants.LOGIN_VALIDATE_TEMPLATE_CODE);
                    break;
                case AliyunSmsConstants.BIND_PHONE_TYPE:
                    request.setTemplateCode(AliyunSmsConstants.BIND_PHONE_VALIDATE_TEMPLATE_CODE);
                    break;
                case AliyunSmsConstants.REGISTERED_TYPE:
                    request.setTemplateCode(AliyunSmsConstants.REGISTERED_VALIDATE_TEMPLATE_CODE);
                    break;
                case AliyunSmsConstants.PASSWORD_TYPE:
                    request.setTemplateCode(AliyunSmsConstants.PASSWORD_VALIDATE_TEMPLATE_CODE);
                    break;
                default:
                    break;
            }
        }
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        String param = new StringBuilder("{code").append(":").append(validateCode).append("}")
                .toString();
        request.setTemplateParam(param);
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        logger.info("短信推送: code " + sendSmsResponse.getCode() + " message " + sendSmsResponse
                .getMessage());
        if (sendSmsResponse.getCode() != null) {
            if (ResultEnum.OK.getMsg().equalsIgnoreCase(sendSmsResponse.getCode())) {
                return ResultEnum.OK;
            }
            if (AliyunSmsConstants.ERRORPHONE_CODE.equalsIgnoreCase(sendSmsResponse.getCode())) {
                return ResultEnum.ERROR_PHONE_NUM;
            }
            if (AliyunSmsConstants.OUT_OF_SERVICE_CODE
                    .equalsIgnoreCase(sendSmsResponse.getCode())) {
                logger.info("短信业务停机");
                return ResultEnum.SEND_ERROR;
            }
        }
        return ResultEnum.SEND_ERROR;
    }


    /**
     * 生成验证码
     */
    public static String makeAuthCode() {
        int authCode = (int) Math.round(Math.random() * (9999 - 1000) + 1000);
        return new StringBuilder().append(authCode).toString();
    }

}
