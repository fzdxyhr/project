package com.yhr.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yhr
 * @version latest
 * @date 2016/7/20
 * @description
 */
@Entity
@Data
@Table(name = "user1")
public class User {
    @Id
    private Long id;
    @Column(name = "user_name")
    private String UserName;
    @Column(name = "password")
    private String password;
}
