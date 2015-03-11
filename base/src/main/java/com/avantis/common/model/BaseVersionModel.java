package com.avantis.common.model;

import com.avantis.annotation.Column;
import com.avantis.annotation.Version;
import com.avantis.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;

public class BaseVersionModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "crt_by")
    private String crt_by;
    @Column(name = "crt_dtetme")
    private Timestamp crt_dtetme;
    @Column(name = "updt_by")
    private String updt_by;
    @Column(name = "updt_dtetme")
    private Timestamp updt_dtetme;
    @Version(name = "ver_no")
    @Column(name = "ver_no")
    private Integer ver_no;

    private boolean ignoreConcurrenceCheck;

    public String getCrt_by() {
        return crt_by;
    }

    public void setCrt_by(String crt_by) {
        this.crt_by = crt_by;
    }

//    @JSON(format= DateUtil.DATETIME_FORMAT)
    public Timestamp getCrt_dtetme() {
        return crt_dtetme;
    }

    public void setCrt_dtetme(Timestamp crt_dtetme) {
        this.crt_dtetme = crt_dtetme;
    }

    public String getCrt_dtetmeStr() {
        if(this.crt_dtetme != null) {
            return DateUtil.formatDateTime(this.crt_dtetme);
        }
        return null;
    }

    public void setCrt_dtetmeStr(String crt_dtetmeStr) {
        if(StringUtils.isNotBlank(crt_dtetmeStr)) {
            this.crt_dtetme = new Timestamp(DateUtil.parseDateTime(crt_dtetmeStr).getTime());
        }
    }

    public String getUpdt_by() {
        return updt_by;
    }

    public void setUpdt_by(String updt_by) {
        this.updt_by = updt_by;
    }

//    @JSON(format=DateUtil.DATETIME_FORMAT)
    public Timestamp getUpdt_dtetme() {
        return updt_dtetme;
    }

    public void setUpdt_dtetme(Timestamp updt_dtetme) {
        this.updt_dtetme = updt_dtetme;
    }

    public String getUpdt_dtetmeStr() {
        if(this.updt_dtetme != null) {
            return DateUtil.formatDateTime(this.updt_dtetme);
        }
        return null;
    }

    public void setUpdt_dtetmeStr(String updt_dtetmeStr) {
        if(StringUtils.isNotBlank(updt_dtetmeStr)) {
            this.updt_dtetme = new Timestamp(DateUtil.parseDateTime(updt_dtetmeStr).getTime());
        }
    }

    public Integer getVer_no() {
        return ver_no;
    }

    public void setVer_no(Integer ver_no) {
        this.ver_no = ver_no;
    }

    public boolean isIgnoreConcurrenceCheck() {
        return ignoreConcurrenceCheck;
    }

    public void setIgnoreConcurrenceCheck(boolean ignoreConcurrenceCheck) {
        this.ignoreConcurrenceCheck = ignoreConcurrenceCheck;
    }
}

