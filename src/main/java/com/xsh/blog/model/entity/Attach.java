package com.xsh.blog.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (Attach)表实体类
 *
 * @author makejava
 * @since 2023-07-17 21:50:18
 */
@TableName("t_attach")
@Data
public class Attach implements Serializable {
    private static final long serialVersionUID = 9202620219949407629L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String fname;

    private String ftype;

    private String fkey;

    private Integer authorId;

    private Integer created;
}

