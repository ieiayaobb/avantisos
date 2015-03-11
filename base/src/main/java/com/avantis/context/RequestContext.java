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


import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    private static final String SESSION_ID = "SESSION_ID";
    private static final String TRANSACTION_ID = "TRANSACTION_ID";

    private Map<String, Object> contextMap = new HashMap<String, Object>();

    public RequestContext addAttribute(String key, Object object) {
        contextMap.put(key, object);
        return this;
    }

    public Object getAttribute(String key) {
        return contextMap.get(key);
    }

    public RequestContext setSessionId(String sessionId) {
        addAttribute(SESSION_ID, sessionId);
        return this;
    }

    public String getSessionId() {
        return (String) getAttribute(SESSION_ID);
    }

    public RequestContext setTransactionId(String transactionId) {
        addAttribute(TRANSACTION_ID, transactionId);
        return this;
    }

    public String getTransactionId() {
        return (String) getAttribute(TRANSACTION_ID);
    }
}
