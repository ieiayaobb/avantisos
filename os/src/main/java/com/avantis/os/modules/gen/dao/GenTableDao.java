/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.avantis.os.modules.gen.dao;


import com.avantis.os.common.persistence.CrudDao;
import com.avantis.os.common.persistence.annotation.MyBatisDao;
import com.avantis.os.modules.gen.entity.GenTable;

/**
 * 业务表DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {
	
}
