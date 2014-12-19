/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.avantis.os.modules.oa.dao;

import com.avantis.os.common.persistence.BaseDao;
import org.springframework.stereotype.Repository;

import com.avantis.os.common.persistence.BaseDao;
import com.avantis.os.common.persistence.Parameter;
import com.avantis.os.modules.oa.entity.Leave;

/**
 * 请假DAO接口
 * @author liuj
 * @version 2013-8-23
 */
@Repository
public class LeaveDao extends BaseDao<Leave> {
	
	public int updateProcessInstanceId(String id,String processInstanceId){
		return update("update Leave set processInstanceId=:p1 where id = :p2", new Parameter(processInstanceId, id));
	}
	
}
