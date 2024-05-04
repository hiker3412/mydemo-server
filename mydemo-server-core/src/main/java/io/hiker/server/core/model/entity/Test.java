package io.hiker.server.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName test
 */
@TableName(value ="test")
@Data
public class Test implements Serializable {

    //测试用户ID
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 测试用户名
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}