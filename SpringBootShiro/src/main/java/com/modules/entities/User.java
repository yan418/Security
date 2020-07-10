package com.modules.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 * @Title: Provider
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private Integer id;
    //用户名
	private String username;
    //用户密码
    private String password;
    //状态  1 开启  0关闭
    private String ticket;

    //角色
    private List<Role> roles;

}
