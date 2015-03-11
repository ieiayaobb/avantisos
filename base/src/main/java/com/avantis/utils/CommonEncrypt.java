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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CommonEncrypt {

    private static final Log logger = LogFactory.getLog(CommonEncrypt.class);

//	public static String encryptMD5(String input){
//		return encrypt(input,"MD5");
//	}

    public static String encryptSHA1(String input){
        return encrypt(input,"SHA-1");
    }

    public static String encrypt(String input,String algorithm){
        if ("".equals(input))
            return "";
        String encryptText;
        // MD5是16位,SHA是20位（这是两种报文摘要的算法）
        try {
            MessageDigest md= MessageDigest.getInstance(algorithm);
            md.update(input.getBytes("UTF-8"));
            encryptText = byteToHex(md.digest());

        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException",e);
            encryptText = "";
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException",e);
            encryptText = "";
        }
        return encryptText;
    }



	// 二行制转字符串
	private static String byteToHex(byte[] input) {
		StringBuilder encryptText = new StringBuilder(32);
        for (byte anInput : input) {
            String stmp = Integer.toHexString(anInput & 0xFF);
            if (stmp.length() == 1)
                encryptText.append("0").append(stmp);
            else
                encryptText.append(stmp);
        }
		return encryptText.toString();
	}

}
