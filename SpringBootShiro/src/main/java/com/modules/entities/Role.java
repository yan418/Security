package com.modules.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体类
 * @Title: Provider
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{

    private Integer id;
    //职称
	private String name;
    //角色名
    private String sn;


}
