package com.avantis.common.model;

import com.avantis.annotation.Column;
import com.avantis.annotation.Pk;
import com.avantis.annotation.Table;
import com.avantis.annotation.Version;
import com.avantis.common.sqlbuilder.TableColumnMapping;
import com.avantis.utils.BeanUtil;
import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.StringEscapeUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean needAudit = true;

    public BaseModel(boolean needAudit) {
        this.needAudit = needAudit;
    }

    public BaseModel() {
    }

    private List<String> amendList = new ArrayList<String>();
    public List<String> getAmendList() {
        return amendList;
    }
    public void setAmendList(List<String> amendList) {
        this.amendList = amendList;
    }

    public boolean isNeedAudit() {
        return needAudit;
    }

    public void setNeedAudit(boolean needAudit) {
        this.needAudit = needAudit;
    }

    public BaseModel addAmendFiled(String amendField) {
        this.amendList.add(amendField);
        return this;
    }

    public String tableName() {
        Table table = this.getClass().getAnnotation(Table.class);
        if (table != null)
            return table.name();
        else
            throw new RuntimeException("undefined POJO @Table, need Table Name(@Table)");
    }

    public List<TableColumnMapping> pks(boolean forInsert) {
        List<TableColumnMapping> ids = new ArrayList<TableColumnMapping>();
        Class<?> clazz = this.getClass();
        LinkedList<Class<?>> allClazz = new LinkedList<Class<?>>();
        while (true) {
            allClazz.add(clazz);
            if (clazz.getName().equals(BaseModel.class.getName())) break;
            clazz = clazz.getSuperclass();
        }
        for (Class _clazz : allClazz) {
            for (Field field : _clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Pk.class)) {
                    Pk pk = field.getAnnotation(Pk.class);
                    if (!pk.auto_increase() || !forInsert) {
                        String tableColumnName = StringUtils.isEmpty(pk.name()) ? field.getName() : pk.name();
                        ids.add(new TableColumnMapping(tableColumnName, field.getName(), true, pk.auto_increase(),pk.jdbcType(),pk.sequence()));
                    }

                }
            }
        }
        return ids;
    }

    public List<TableColumnMapping> nonPKColumns() {
        List<TableColumnMapping> columns = new ArrayList<TableColumnMapping>();
        Class<?> clazz = this.getClass();
        LinkedList<Class<?>> allClazz = new LinkedList<Class<?>>();
        while (true) {
            allClazz.add(clazz);
            if (clazz.getName().equals(BaseModel.class.getName())) break;
            clazz = clazz.getSuperclass();
        }
//        while (!allClazz.isEmpty()) {
//            getTableColumns(columns, allClazz.removeLast());
//        }

        for(Class<?> _class : allClazz){
            getTableColumns(columns, _class);
        }

        return columns;
    }

    public TableColumnMapping version() {
        Class<?> clazz = this.getClass();
        LinkedList<Class<?>> allClazz = new LinkedList<Class<?>>();
        while (true) {
            allClazz.add(clazz);
            if (clazz.getName().equals(BaseModel.class.getName())) break;
            clazz = clazz.getSuperclass();
        }
        TableColumnMapping version= null;
        while (!allClazz.isEmpty() && version == null) {
             version =  getTableVersion( allClazz.removeLast());
        }
        return version;

    }

    public List<TableColumnMapping> allColumns() {
        List<TableColumnMapping> columns = pks(false);
        columns.addAll(nonPKColumns());
        return columns;
    }

    private void getTableColumns(List<TableColumnMapping> columns, Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                String tableColumnName = StringUtils.isEmpty(column.name()) ? field.getName() : column.name();
                TableColumnMapping tableColumnMapping = new TableColumnMapping(tableColumnName, field.getName(),column.jdbcType());
                if (column.ignore() && columns.contains(tableColumnMapping)) {
                    columns.remove(tableColumnMapping);
                } else {
                    columns.add(tableColumnMapping);
                }

            }
        }
    }

    private TableColumnMapping getTableVersion(Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Version.class)) {
                Version version = field.getAnnotation(Version.class);
                String tableColumnName = StringUtils.isEmpty(version.name()) ? field.getName() : version.name();
                return new TableColumnMapping(tableColumnName, field.getName());
            }
        }
        return null;
    }


    public List<String> getTableColumns() {
        List<TableColumnMapping> allColumns = allColumns();
        List<String> columns = new ArrayList<String>();
        for (TableColumnMapping tableColumnMapping : allColumns) {
            columns.add(tableColumnMapping.getTableColumn());
        }
        return columns;
    }

    public String getTableColumnsImage() {
        StringBuilder columnsImage = new StringBuilder();
        for (String column : getTableColumns()) {
            columnsImage.append(column).append(",");
        }
        return columnsImage.deleteCharAt(columnsImage.length()-1).toString();
    }

    public List<String> getTableColumnValues(){
        List<String> columnValues = new ArrayList<String>();
        List<TableColumnMapping> allColumns = allColumns();
        for (TableColumnMapping tableColumnMapping : allColumns) {
            Object value = BeanUtil.getObjPropertyValue(this,tableColumnMapping.getObjectField());
            if(value!=null){
                value = value.toString().replaceAll(",","&#44;"); //replace comma with html encode
            }else{
                value="NULL";
            }
            columnValues.add(value.toString());
        }
        return columnValues;
    }

//    public void escapeHtmlColumnValues() {
//        List<TableColumnMapping> allColumns = allColumns();
//        for (TableColumnMapping tableColumnMapping : allColumns) {
//            Object value = BeanUtil.getObjPropertyValue(this, tableColumnMapping.getObjectField());
//            if (value != null && value instanceof String) {
//                value = StringEscapeUtils.escapeHtml4((String) value);
//                BeanUtil.setObjPropertyValue(this, tableColumnMapping.getObjectField(), value);
//            }
//        }
//    }

    // Used for pagination
    private int rowId;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }
}

