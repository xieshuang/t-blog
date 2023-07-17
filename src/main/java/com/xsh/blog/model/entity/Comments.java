package com.xsh.blog.model.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (Comments)表实体类
 *
 * @author makejava
 * @since 2023-07-17 22:33:39
 */
@Data
@TableName("t_comments")
public class Comments implements Serializable {
    private static final long serialVersionUID = 1939191127263215241L;
    @TableId(type = IdType.AUTO)
    private Integer coid;

    private Integer cid;

    private Integer created;

    private String author;

    private Integer authorId;

    private Integer ownerId;

    private String mail;

    private String url;

    private String ip;

    private String agent;

    private String content;

    private String type;

    private String status;

    private Integer parent;
}



