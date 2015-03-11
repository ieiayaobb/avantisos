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


import java.sql.Timestamp;
import java.util.List;


public class UserContext {
    private String user_id;
    private String sessionId;
    private Timestamp login_date;
    private String name;
    private String incidentId;
    private String incidentTypeCode;
    private String deploymentTypeCode;
    private String switchPortal;

    private List<String> menuTypeList;

    public static final String UP_KEY = "LOGIN_USER_PROFILE";

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Timestamp login_date) {
        this.login_date = login_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentTypeCode() {
        return incidentTypeCode;
    }

    public void setIncidentTypeCode(String incidentTypeCode) {
        this.incidentTypeCode = incidentTypeCode;
    }

    public String getDeploymentTypeCode() {
        return deploymentTypeCode;
    }

    public void setDeploymentTypeCode(String deploymentTypeCode) {
        this.deploymentTypeCode = deploymentTypeCode;
    }

    public String getSwitchPortal() {
        return switchPortal;
    }

    public void setSwitchPortal(String switchPortal) {
        this.switchPortal = switchPortal;
    }

    public List<String> getMenuTypeList() {
        return menuTypeList;
    }

    public void setMenuTypeList(List<String> menuTypeList) {
        this.menuTypeList = menuTypeList;
    }
}
