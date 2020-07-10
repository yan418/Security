package com.modules.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * 用户实体类
 * @Title: Provider
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority,Serializable{

    private Integer id;
    //职称
	private String name;
    //角色名
    private String sn;

    // @JsonIgnore json 转对象，忽略这些get,set
    @JsonIgnore
    @Override
    public String getAuthority() {
        return sn;
    }

}
