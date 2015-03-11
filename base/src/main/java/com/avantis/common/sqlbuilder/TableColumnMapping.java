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

package com.avantis.common.sqlbuilder;

public class TableColumnMapping {
    private String tableColumn;
    private String jdbcType;
    private String objectField;
    private String sequence;
    private boolean isPk = false;
    private boolean isIncreased = false;

    public TableColumnMapping() {
    }

    public TableColumnMapping(String tableColumn, String objectField) {
        this.tableColumn = tableColumn;
        this.objectField = objectField;
    }

    public TableColumnMapping(String tableColumn, String objectField, String jdbcType) {
        this.tableColumn = tableColumn;
        this.objectField = objectField;
        this.jdbcType = jdbcType;
    }

    public TableColumnMapping(String tableColumn, String objectField, boolean isPk, boolean isIncreased,String jdbcType) {
        this.tableColumn = tableColumn;
        this.objectField = objectField;
        this.isPk = isPk;
        this.isIncreased = isIncreased;
        this.jdbcType = jdbcType;
    }

    public TableColumnMapping(String tableColumn, String objectField, boolean isPk, boolean isIncreased,String jdbcType,String sequence) {
        this.tableColumn = tableColumn;
        this.objectField = objectField;
        this.isPk = isPk;
        this.isIncreased = isIncreased;
        this.jdbcType = jdbcType;
        this.sequence = sequence;
    }


    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean pk) {
        isPk = pk;
    }

    public boolean isIncreased() {
        return isIncreased;
    }

    public void setIncreased(boolean increased) {
        isIncreased = increased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableColumnMapping)) return false;

        TableColumnMapping that = (TableColumnMapping) o;

        if (tableColumn != null ? !tableColumn.equals(that.tableColumn) : that.tableColumn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tableColumn != null ? tableColumn.hashCode() : 0;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
