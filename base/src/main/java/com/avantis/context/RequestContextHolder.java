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

package com.avantis.context;


public class RequestContextHolder {

    private static ThreadLocal<RequestContext> contextThreadLocal = new ThreadLocal<RequestContext>();

    public static RequestContext getRequestContext() {
        RequestContext requestContext = contextThreadLocal.get();
        if(requestContext == null){
            requestContext = new RequestContext();
            contextThreadLocal.set(requestContext);
        }
        return requestContext;
    }

    public static void clear() {
        contextThreadLocal.remove();
    }

    public static void setRequestContext(RequestContext requestContext) {
        contextThreadLocal.set(requestContext);
    }
}
