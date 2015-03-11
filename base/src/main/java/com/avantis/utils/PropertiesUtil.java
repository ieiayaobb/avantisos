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

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PropertiesUtil {

    private static Map<String, Object> table = new ConcurrentHashMap<String, Object>();
    private static long reloadPeriod = 5000;
    private static final Log log = LogFactory.getLog(PropertiesUtil.class);


    /**
     * get property value by key with no exception
     *
     * @param propertyFile Property File Name
     * @param key          Property key
     * @return Property Value
     */
    public static String getPropertyValueNoEx(String propertyFile, String key) {
        try {
            return getPropertyValue(propertyFile, key);
        } catch (Exception exception) {
            //ignore
        }
        return null;
    }

    /**
     * get property value by key with no exception
     *
     * @param propertyFile Property File Name
     * @param key          Property key
     * @return Property Value
     * @throws Exception if any error
     */
    public static String getPropertyValue(String propertyFile, String key) throws Exception {
        String result = null;
        PropertiesConfiguration config = getConfig(propertyFile);
        Object returnObj = config.getProperty(key);
        if (returnObj != null) {
            if (String.class.getName().equals(returnObj.getClass().getName())) {
                result = (String) returnObj;
            } else if (ArrayList.class.getName().equals(returnObj.getClass().getName())) {
                returnObj = ((ArrayList<?>) returnObj).get(0);
                if (String.class.getName().equals(returnObj.getClass().getName())) {
                    result = (String) returnObj;
                }
            }
        }

        return result;
    }

    /**
     * Get property string value for list values by key
     *
     * @param propertyFile Property File Name
     * @param key          Property key
     * @return Property Value
     * @throws Exception if any error
     */
    public static String getList2String(String propertyFile, String key) throws Exception {
        String result = null;
        PropertiesConfiguration config = getConfig(propertyFile);
        Object returnObj = config.getProperty(key);
        if (returnObj != null) {
            if (String.class.getName().equals(returnObj.getClass().getName())) {
                result = (String) returnObj;
            } else if (ArrayList.class.getName().equals(returnObj.getClass().getName())) {
                @SuppressWarnings("unchecked")
                List<String> pList = (List<String>) returnObj;
                StringBuilder buff = new StringBuilder();
                for (int i = 0, j = pList.size(); i < j; i++) {
                    buff.append(pList.get(i));
                    if (i != j - 1)
                        buff.append(",");
                }
                result = buff.toString();
            }
        }

        return result;
    }

    /**
     * Get property string array value by key
     *
     * @param propertyFile Property File Name
     * @param key          Property key
     * @return Property Value
     * @throws Exception if any error
     */

    public static String[] getPropertyValueArray(String propertyFile, String key) throws Exception {
        if (StringUtils.isBlank(key))
            throw new Exception("Property key is blank!");
        PropertiesConfiguration config = getConfig(propertyFile);
        return config.getStringArray(key);
    }

    /**
     * Get configuration of the specific Property file
     *
     * @param propertyFile Property File Name
     * @return PropertiesConfiguration
     * @throws Exception if any error
     */
    private static PropertiesConfiguration getConfig(String propertyFile) throws Exception {

        if (StringUtils.isBlank(propertyFile))
            throw new Exception("Property File Name is blank");

        PropertiesConfiguration config = (PropertiesConfiguration) table.get(propertyFile);

        if (config == null) {
            config = new PropertiesConfiguration(propertyFile);
            FileChangedReloadingStrategy fs = new FileChangedReloadingStrategy();
            fs.setRefreshDelay(reloadPeriod);
            config.setReloadingStrategy(fs);
            table.put(propertyFile, config);
        }
        return config;

    }


    public static class PropertiesInstance {

        private String propertiesFile;

        public PropertiesInstance(String propertiesFile) {
            this.propertiesFile = propertiesFile;
        }

        public String getPropertyValueNoEx(String key) {
            return PropertiesUtil.getPropertyValueNoEx(propertiesFile,key);
        }

        public String getPropertyValue( String key) throws Exception {
            return PropertiesUtil.getPropertyValue(propertiesFile, key);
        }

        public String getList2String(String key) throws Exception {
            return PropertiesUtil.getList2String(propertiesFile, key);
        }

        public String[] getPropertyValueArray( String key) throws Exception {
            return PropertiesUtil.getPropertyValueArray(propertiesFile, key);
        }
    }

    public final static  PropertiesInstance COMMON = new PropertiesInstance("miidss3-common.properties");
    public final static  PropertiesInstance BASE = new PropertiesInstance("miidss3-base.properties");
    public final static  PropertiesInstance AUDIT = new PropertiesInstance("audit-message.properties");
    public final static  PropertiesInstance ACTIVE_DIRECTORY = new PropertiesInstance("miidss3-ad.properties");

   
}
