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
package com.avantis.utils;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MethodUtil {
    private final static Map<Class<?>, LocalVariableTableParameterNameDiscoverer> parameterNamesCache =
            new ConcurrentHashMap<Class<?>, LocalVariableTableParameterNameDiscoverer>();

    /**
     * Get method parameter names
     * @param method  Method
     * @return method parameter names
     */
    public static String[] getMethodParameter(Method method) {
        Class<?> declaringClass = method.getDeclaringClass();
        LocalVariableTableParameterNameDiscoverer paramNameResolver = parameterNamesCache.get(declaringClass);
        if (paramNameResolver == null) {
            paramNameResolver = new LocalVariableTableParameterNameDiscoverer();
            parameterNamesCache.put(declaringClass, paramNameResolver);
        }
        String[] parameterNames = paramNameResolver.getParameterNames(method);
        if(parameterNames == null){
            parameterNames = new String[0];
        }
        return parameterNames;
    }
}
