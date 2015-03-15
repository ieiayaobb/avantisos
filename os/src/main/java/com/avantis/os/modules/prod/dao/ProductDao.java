package com.avantis.os.modules.prod.dao;

import com.avantis.os.common.persistence.CrudDao;
import com.avantis.os.common.persistence.annotation.MyBatisDao;
import com.avantis.os.modules.prod.entity.Product;

/**
 * Created by admin on 2015/3/12.
 */
@MyBatisDao
public interface ProductDao extends CrudDao<Product> {
}
