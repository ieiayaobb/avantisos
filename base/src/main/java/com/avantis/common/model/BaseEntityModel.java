package com.avantis.common.model;

import com.avantis.annotation.Tab;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntityModel extends BaseVersionModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5046224320674669051L;
	private String tabName;
	
	private Integer entity_seq_id;
	

	private Integer entity_type_id;
	
	private Integer entity_rec_seq_id;
	
	private String entity_rec_id;
	
	private String displayName;
	
	private String headDisplayName;
	
	public abstract Integer getEntity_seq_id();
	
	public void setEntity_seq_id(Integer entity_seq_id) {
		this.entity_seq_id = entity_seq_id;
	}
	
	public abstract Integer getEntity_type_id();

	public void setEntity_type_id(Integer entity_type_id) {
		this.entity_type_id = entity_type_id;
	}

	public abstract Integer getEntity_rec_seq_id();

	public void setEntity_rec_seq_id(Integer entity_rec_seq_id) {
		this.entity_rec_seq_id = entity_rec_seq_id;
	}

	public abstract String getEntity_rec_id();

	public void setEntity_rec_id(String entity_rec_id) {
		this.entity_rec_id = entity_rec_id;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getHeadDisplayName() {
		return headDisplayName;
	}

	public void setHeadDisplayName(String headDisplayName) {
		this.headDisplayName = headDisplayName;
	}

	public List<String> getAmendListWithTabName(){
		List<String> columns = new ArrayList<String>();
		
		for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Tab.class)) {
            	Tab tab = field.getAnnotation(Tab.class);
                String tabName = StringUtils.isEmpty(tab.name()) ? field.getName() : tab.name();
                
                if(tabName.contains(",")){
                	String[] tabNames = tabName.split(",");
	                for(String eachTabName : tabNames){
	                	if(StringUtils.equals(eachTabName, this.tabName)){
	                		columns.add(field.getName() );
	                	}
	                }
                }else{
                	if(StringUtils.equals(tabName, this.tabName)){
                		columns.add(field.getName() );
                	}
                }
            }
        }
		return columns;
	}
}
