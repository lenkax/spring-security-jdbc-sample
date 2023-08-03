package com.lucky.security.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lucky.security.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 基础字段填充
 * @author: lenka
 * @date: 2023-05-04 2:25 PM
 */
@Component
public class BaseFieldFilledHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createBy = getFieldValByName("createdBy", metaObject);
        Object createDate = getFieldValByName("createdDate", metaObject);

        if (createBy == null) {
            this.strictInsertFill(metaObject, "createdBy", String.class, Optional.ofNullable(SecurityUtils.getCurrentUsername()).orElse("system"));
        }

        if (createDate == null) {
            this.strictInsertFill(metaObject, "createdDate", LocalDateTime.class, LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateBy = getFieldValByName("updatedBy", metaObject);
        Object updateDate = getFieldValByName("updatedDate", metaObject);

        if (updateBy == null) {
            this.strictUpdateFill(metaObject, "updatedBy", String.class, Optional.ofNullable(SecurityUtils.getCurrentUsername()).orElse("system"));
        }

        if (updateDate == null) {
            this.strictUpdateFill(metaObject, "updatedDate", LocalDateTime.class, LocalDateTime.now());
        }
    }
}
