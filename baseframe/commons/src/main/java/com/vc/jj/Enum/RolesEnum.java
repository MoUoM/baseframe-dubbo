package com.vc.jj.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RolesEnum {
    ADMIN("1","admin"),
    CUSTERMER("2","会员"),
    NORMAL_STATUS("0","正常状态"),
    LOCK_STATUS("1","锁定状态"),
    ROLE_IS_EXITES("205","角色已经存在"),
    ROLE_IS_USERING("406","角色使用中"),
   ;
    private @Getter
     String roleCode  ;

    private @Getter
    final String roleMessage;
}
