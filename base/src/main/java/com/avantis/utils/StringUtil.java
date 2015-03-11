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

import org.apache.commons.lang.StringUtils;

public class StringUtil {
    public static String translateStr(String strVal) {
        if(StringUtils.isNotBlank(strVal)) {
            if(strVal.contains("\"")) {
                strVal = strVal.replaceAll("\"", "\"\"");
            }
            strVal =  "\"" + strVal + "\"";
            return strVal;
        }
        return null;
    }
}
