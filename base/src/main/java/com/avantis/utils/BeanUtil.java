/*
* =========================================================================
*  Copyright ?2012 NCS Pte. Ltd. All Rights Reserved
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

package com.avantis.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtil {

    private static final Log logger = LogFactory.getLog(BeanUtil.class);

    public static Object getObjPropertyValue(Object obj, String objectField) {
        try {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(obj.getClass(), objectField);
            if(propertyDescriptor!=null) {
                Method method = propertyDescriptor.getReadMethod();
                if(method != null) {
                    return method.invoke(obj);
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("Fail to get property value",e);
            //ignore this value
        } catch (InvocationTargetException e) {
            logger.error("Fail go get property value",e);
            //ignore this value
        }
        return null;
    }

    public static void setObjPropertyValue(Object obj, String objectField, Object value) {
        try {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(obj.getClass(), objectField);
            if(propertyDescriptor!=null) {
                Method method = propertyDescriptor.getWriteMethod();
                if(method  != null){
                    Object[] values = new Object[1];
                    values[0] = value;
                    method.invoke(obj,values);
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("Fail to set property value",e);
            //ignore this value
        } catch (InvocationTargetException e) {
            logger.error("Fail go set property value",e);
            //ignore this value
        }catch (IllegalArgumentException e){
            logger.error("Fail go set property value",e);
            //ignore this value
        }
    }
}
