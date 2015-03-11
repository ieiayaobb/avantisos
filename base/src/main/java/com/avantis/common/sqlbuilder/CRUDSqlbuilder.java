/*
* =========================================================================
*  Copyright ?2014 NCS Pte. Ltd. All Rights Reserved
*
*  This software is confidential and proprietary to NCS Pte. Ltd. You shall
*  use this software only in accordance with the terms of the license
*  agreement you entered into with NCS.  No aspect or part or all of this
*  software may be reproduced, modified or disclosed without full and
*  direct written authorisation from NCS.
*
*  NCS SUPPLIES THIS SOFTWARE ON AN ?AS IS? BASIS. NCS MAKES NO
*  REPRESENTATIONS OR WARRANTIES, EITHER EXPRESSLY OR IMPLIEDLY, ABOUT THE
*  SUITABILITY OR NON-INFRINGEMENT OF THE SOFTWARE. NCS SHALL NOT BE LIABLE
*  FOR ANY LOSSES OR DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
*  MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*  =========================================================================
*/

package com.avantis.common.sqlbuilder;


import com.avantis.common.exception.AmendFieldsNotProvidedException;
import com.avantis.common.exception.PkValueNotProvidedException;
import com.avantis.common.model.BaseModel;
import com.avantis.common.model.BaseVersionModel;
import com.avantis.utils.BeanUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class CRUDSqlbuilder<T extends BaseModel> {
    private static final Log logger = LogFactory.getLog(CRUDSqlbuilder.class);

    public String delete(T obj) {
        BEGIN();
        DELETE_FROM(obj.tableName());
        for (TableColumnMapping id : obj.pks(false)) {
            WHERE(id.getTableColumn() + "=#{" + id.getObjectField() + "}");
        }
        return SQL();
    }

    public String queryObject(T obj) throws PkValueNotProvidedException {
        BEGIN();
        SELECT("*");
        FROM(obj.tableName());

        for (TableColumnMapping id : obj.pks(false)) {
            if (BeanUtil.getObjPropertyValue(obj, id.getObjectField()) == null) {
                throw new PkValueNotProvidedException();
            }
            WHERE(id.getTableColumn() + "=#{" + id.getObjectField() + "}");
        }
        return SQL();
    }

    public String queryList(T obj) {
        BEGIN();
        SELECT("*");
        FROM(obj.tableName());
        for (TableColumnMapping id : obj.pks(false)) {
            Object objPropertyValue = BeanUtil.getObjPropertyValue(obj, id.getObjectField());
            if (objPropertyValue != null && !(id.isPk() && id.isIncreased() && objPropertyValue.equals(0))) {
                WHERE(id.getTableColumn() + "=#{" + id.getObjectField() + "}");
            }
        }
        for (TableColumnMapping column : obj.nonPKColumns()) {
            if (BeanUtil.getObjPropertyValue(obj, column.getObjectField()) != null) {
                WHERE(column.getTableColumn() + "=#{" + column.getObjectField() + "}");
            }
        }

        return SQL();
    }

    public String queryAll(Class<T> clazz) {
        BaseModel obj;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Cannot initialize Model Object based on class : " + clazz.getName());
        }
        BEGIN();
        SELECT("*");
        FROM(obj.tableName());
        return SQL();
    }



    public String insert(T obj) {
        BEGIN();
        INSERT_INTO(obj.tableName());
        for (TableColumnMapping id : obj.pks(true)) {
            String jdbcType = id.getJdbcType();
            String jdbcTypePart = jdbcType.equals("") ? "" : ",jdbcType=" + jdbcType;
            VALUES(id.getTableColumn(), "#{" + id.getObjectField()+ jdbcTypePart  + "}");
        }
        //important, for these fields which may has NULL value, must include jdbcType attribute,
        // otherwise, mybatis will throws exception when the value is null.
        //2014-05027 ignore the NULL value by default
        for (TableColumnMapping column : obj.nonPKColumns()) {
            if (BeanUtil.getObjPropertyValue(obj, column.getObjectField()) != null) {
                String jdbcType = column.getJdbcType();
                String jdbcTypePart = jdbcType.equals("") ? "" : ",jdbcType=" + jdbcType;
                VALUES(column.getTableColumn(), "#{" + column.getObjectField() + jdbcTypePart + "}");
            }
        }
        if(obj instanceof BaseVersionModel){
            TableColumnMapping version= obj.version();
            if(version != null) {
                VALUES(version.getTableColumn(), "1");
            }
        }
        return SQL();
    }

    public String update(T obj) throws AmendFieldsNotProvidedException {
        BEGIN();
        UPDATE(obj.tableName());
        boolean setFieldsIsBlank = true;
        TableColumnMapping version = null;
        if(obj instanceof BaseVersionModel){
            version= obj.version();
        }
        for (TableColumnMapping column : obj.nonPKColumns()) {
            if (!column.getObjectField().equals("crt_by")&&!column.getObjectField().equals("crt_dtetme")&&!column.getObjectField().equals("ver_no")
                    && (obj.getAmendList().isEmpty() || obj.getAmendList().contains(column.getObjectField()))) {
                String jdbcType = column.getJdbcType();
                String jdbcTypePart = jdbcType.equals("") ? "" : ",jdbcType=" + jdbcType;
                SET(column.getTableColumn() + "=#{" + column.getObjectField() + jdbcTypePart + "}");
                setFieldsIsBlank = false;
            }
        }
        if(setFieldsIsBlank)  {
            throw new AmendFieldsNotProvidedException();
        }
        if(version!=null){
            SET(version.getTableColumn() + "=" + version.getTableColumn() + "+1");
        }
        for (TableColumnMapping id : obj.pks(false)) {
            if (BeanUtil.getObjPropertyValue(obj, id.getObjectField()) == null) {
                throw new PkValueNotProvidedException();
            }
            String jdbcType = id.getJdbcType();
            String jdbcTypePart = jdbcType.equals("") ? "" : ",jdbcType=" + jdbcType;
            WHERE(id.getTableColumn() + "=#{" + id.getObjectField()+ jdbcTypePart + "}");
        }
        if(version!=null && !((BaseVersionModel) obj).isIgnoreConcurrenceCheck()){
            WHERE(version.getTableColumn() + "=#{" + version.getObjectField() + "}"); 
        }


        return SQL();
    }
}
