/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.avantis.os.modules.sys.dao;

import com.avantis.os.common.persistence.CrudDao;
import com.avantis.os.common.persistence.annotation.MyBatisDao;
import com.avantis.os.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
