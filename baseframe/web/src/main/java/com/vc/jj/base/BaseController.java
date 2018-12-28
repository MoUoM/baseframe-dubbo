package com.vc.jj.base;

import com.alibaba.fastjson.JSON;
import com.vc.jj.dto.ResultBean;
import com.vc.jj.dto.TokenModel;
import com.vc.jj.dto.YdResultDTO;
import com.vc.jj.util.StringEscapeEditor;
import com.vc.jj.util.SysConst;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description：基础 controller
 * @author： loysen
 * @date：2015/10/1 14:51
 */
public abstract class BaseController {

    // 控制器本来就是单例，这样似乎更加合理
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(BaseController.class);

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
    }


    /**
     * 获取当前登录用户名
     * @return {String}
     */
    public String getUserName() {
        return this.getLoginUser().getLoginName();
    }


    /**
     * 获取当前登录用户对象
     * @return {getLoginUser}
     */
    public TokenModel getLoginUser() {
        return (TokenModel)  getRequest().getAttribute(SysConst.CURRENT_USER);
    }

    /**
     * 获取当前登录用户id
     * @return {Long}
     */
    public Long getUserId() {
        Long userId = -1L;
        if( getRequest() == null|| getRequest().getAttribute(SysConst.CURRENT_USER_ID)== null ) {
            userId = -1L;
        }else{
            userId = Long.valueOf(getRequest().getAttribute(SysConst.CURRENT_USER_ID).toString() );
        }
        return   Math.abs(userId );
    }

    public  boolean isDriver(){
        Long userId = -1L;
        if( getRequest() == null|| getRequest().getAttribute(SysConst.CURRENT_USER_ID)== null ) {
            userId = -1L;
        }else{
            userId = Long.valueOf(getRequest().getAttribute(SysConst.CURRENT_USER_ID).toString() );
        }
        if(userId<0){
            return  true;
        }
        return  false;
    }

    /**
     * 获取当前登录用户名 AppId
     * @return {String}
     */
    public String getAppId() {
        return this.getLoginUser().getAppid();
    }

    public HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


    /**
     * 获取客户端的IP地址
     * */
    /**
     * 获取当前网络ip
     * @return
     */
    public String getIpAddr(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


    public ResultBean   rResultBean (Exception e){
        YdResultDTO ydResultDTO = new YdResultDTO();
        ydResultDTO.setFlag(false);
        ydResultDTO.setErrorMessage(e.getMessage());
        return ResultBean.build4ResultDTO(ydResultDTO);

    }

}
