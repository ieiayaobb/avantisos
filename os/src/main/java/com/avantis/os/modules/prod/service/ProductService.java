package com.avantis.os.modules.prod.service;

import com.avantis.os.common.service.CrudService;
import com.avantis.os.modules.cms.dao.ArticleDao;
import com.avantis.os.modules.cms.entity.Article;
import com.avantis.os.modules.prod.dao.ProductDao;
import com.avantis.os.modules.prod.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2015/3/12.
 */
@Service
public class ProductService extends CrudService<ProductDao, Product> {

    public List<Product> findList() {
        return dao.findAllList(new Product());
    }

    @Transactional(readOnly = false)
    public void addNewProduct(Product product){
        product.preInsert();
        product.setDateTime(new Date());
        product.setIsOut(true);
        dao.insert(product);
    }

    @Transactional(readOnly = false)
    public void update(Product product){
        dao.update(product);
    }
}
