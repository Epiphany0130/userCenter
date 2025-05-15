package com.guyuqi.usercenterback.model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author GuYuqi
 * @version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1762276744925620090L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
