package com.lucky.security.domain.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: lenka
 * @date: 2023-05-03 11:19 AM
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "created_date", fill = FieldFill.INSERT)
    private LocalDateTime createdDate;

    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "updated_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedDate;

    @TableLogic
    @TableField("deleted")
    private Boolean deleted;
}
