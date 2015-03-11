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

package com.avantis.common.mapper;

import com.avantis.common.model.BaseModel;
import com.avantis.common.sqlbuilder.CRUDSqlbuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BaseMapper<T extends BaseModel> {
    @DeleteProvider(type = CRUDSqlbuilder.class,method = "delete")
    public void delete(T obj);

    @InsertProvider(type = CRUDSqlbuilder.class,method = "insert")
//    @Options(useGeneratedKeys = true)
    public int insert(T obj);

    @UpdateProvider(type = CRUDSqlbuilder.class,method = "update")
    public int update(T obj);

    @SelectProvider(type = CRUDSqlbuilder.class,method = "queryObject")
    @ResultMap(value = "object_result")
    public T queryObject(T obj);

    @SelectProvider(type = CRUDSqlbuilder.class,method = "queryObject")
    @ResultMap(value = "object_result")
    @Options(flushCache = true)
    public T queryObjectWithoutCache(T obj);

    @SelectProvider(type = CRUDSqlbuilder.class,method = "queryList")
    @ResultMap(value = "object_result")
    public List<T> queryList(T obj);

    @SelectProvider(type = CRUDSqlbuilder.class,method = "queryAll")
    @ResultMap(value = "object_result")
    public List<T> queryAll(Class<T> clazz);

}
