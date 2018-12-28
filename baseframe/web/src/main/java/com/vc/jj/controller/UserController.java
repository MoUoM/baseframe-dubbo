package com.vc.jj.controller;

import com.vc.jj.dto.PasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vc.jj.api.DriverApi;
import com.vc.jj.api.UserModelApi;
import com.vc.jj.base.BaseController;
import com.vc.jj.dto.ResultBean;
import com.vc.jj.dto.UserDto;
import com.vc.jj.dto.YdResultDTO;
import com.vc.jj.util.StringUtils;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private DriverApi driverApi;
    @Autowired
    private UserModelApi userModelApi;

    /**
     * 用户注册
     * */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  register(@RequestBody UserDto userDto){
        YdResultDTO dto =  new YdResultDTO();
        dto.setFlag(true);
        if(StringUtils.isEmpty(userDto.getPhone())){
            dto.setErrorMessage("用戶名不能为空");
            dto.setStatusCode("500");
            return ResultBean.build4ResultDTO(dto);
        }
        if(StringUtils.isEmpty(userDto.getVidateCode())){
            dto.setErrorMessage("验证码不能为空");
            dto.setStatusCode("500");
            return ResultBean.build4ResultDTO(dto);
        }
        if(StringUtils.isEmpty(userDto.getPassword())){
            dto.setErrorMessage("密码不能为空");
            dto.setStatusCode("500");
            return ResultBean.build4ResultDTO(dto);
        }
        try {
            return ResultBean.build4ResultDTO(driverApi.userRegister(userDto));
        } catch (Exception e) {
            return  rResultBean(e);
        }
    }

    /**
     * 用户登录
     * */
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  login(@RequestBody UserDto userDto){
        YdResultDTO dto =  new YdResultDTO();
        dto.setFlag(true);
        if(StringUtils.isEmpty(userDto.getPhone())){
            dto.setErrorMessage("用戶名不能为空");
            dto.setStatusCode("500");
            return ResultBean.build4ResultDTO(dto);
        }
        if(StringUtils.isEmpty(userDto.getPassword())){
            dto.setErrorMessage("密码不能为空");
            dto.setStatusCode("500");
            return ResultBean.build4ResultDTO(dto);
        }
        if(StringUtils.isEmpty(userDto.getDeviceId())){
            dto.setErrorMessage("不是手机登录！");
            dto.setStatusCode("500");
            return ResultBean.build4ResultDTO(dto);
        }
        try {
            return ResultBean.build4ResultDTO(driverApi.userLogin(userDto));
        } catch (Exception e) {
            return  rResultBean(e);
        }
    }

    /**
     * 修改密码
     * */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  updateUserPassword(@RequestBody PasswordDto passwordDto){
        try {
            YdResultDTO dto =  new YdResultDTO();
            dto.setFlag(true);
            if(StringUtils.isBlank(passwordDto.getPhone())){
                dto.setErrorMessage("用戶名不能为空");
                dto.setStatusCode("500");
                return ResultBean.build4ResultDTO(dto);
            }
            if(StringUtils.isBlank(passwordDto.getValidateCode())){
                dto.setErrorMessage("验证码不能为空");
                dto.setStatusCode("500");
                return ResultBean.build4ResultDTO(dto);
            }
            if(StringUtils.isBlank(passwordDto.getPassword())){
                dto.setErrorMessage("密码不能为空");
                dto.setStatusCode("500");
                return ResultBean.build4ResultDTO(dto);
            }
            return ResultBean.build4ResultDTO(driverApi.updateUserPassword(passwordDto));
        } catch (Exception e) {
            return  rResultBean(e);
        }

    }


    /**
     * 用户个人信息
     * */
	@RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  getUserInfo(){
        try {
        	System.out.println("userid: " + getUserId());
            return ResultBean.build4ResultDTO(userModelApi.getUserInfo(getUserId()));
        } catch (Exception e) {
            e.printStackTrace();
            return  rResultBean(e);
        }
    }

    /**
     * 修改用户个人信息
     * */
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultBean  updateUserInfo(@RequestBody UserDto userDto){
        YdResultDTO dto =  new YdResultDTO();
        try {
            userDto.setUserId(getUserId());
            boolean isExist = false;
            if(StringUtils.isNotBlank(userDto.getAddr1())){
                isExist = true;
            }
            if(StringUtils.isNotBlank(userDto.getAddr2())){
                isExist = true;
            }
            if(StringUtils.isNotBlank(userDto.getHeadImgUrl())){
                isExist = true;
            }
            if(StringUtils.isNotBlank(userDto.getContent())){
                isExist = true;
            }
            if(StringUtils.isNotBlank(userDto.getBirthday())){
                isExist = true;
            }
            if(StringUtils.isNotBlank(userDto.getNickname())){
                isExist = true;
            }
            dto.setFlag(true);
            if(!isExist){
                dto.setErrorMessage("必须传入需要修改的参数");
                dto.setStatusCode("500");
                return ResultBean.build4ResultDTO(dto);
            }

            return ResultBean.build4ResultDTO(userModelApi.updateUserInfo(userDto));
        } catch (Exception e) {
            e.printStackTrace();
            return  rResultBean(e);
        }
    }

}
