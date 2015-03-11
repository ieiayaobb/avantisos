package com.avantis.common.service;


import com.avantis.BaseConstant;
import com.avantis.common.exception.ConcurrencyUpdateException;
import com.avantis.common.exception.DuplicateInsertException;
import com.avantis.common.mapper.BaseMapper;
import com.avantis.common.model.BaseEntityModel;
import com.avantis.common.model.BaseVersionModel;
import com.avantis.common.model.BaseModel;
import com.avantis.common.sequence.SqlServerSequenceGenerator;
import com.avantis.common.sqlbuilder.TableColumnMapping;
import com.avantis.context.RequestContextHolder;
import com.avantis.utils.ApplicationContextHolder;
import com.avantis.utils.BeanUtil;
import com.avantis.utils.DateUtil;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;

import java.sql.Timestamp;
import java.util.List;

public class BaseModelService<T extends BaseModel, S extends BaseMapper<T>> extends BaseService{

    protected S mapper;

    public BaseModelService() {
    }

    public BaseModelService(S mapper) {
        super();
        this.mapper = mapper;
    }

    public void setMapper(S mapper) {
        this.mapper = mapper;
    }

    public void insert(T t) {
        if(t == null) {
            throw new NullArgumentException("Model TO");
        }
        if (t instanceof BaseVersionModel) {
            Timestamp currentDateTime = DateUtil.currentDateTime();
			((BaseVersionModel)t).setCrt_by("");
			((BaseVersionModel)t).setCrt_dtetme(currentDateTime);
            ((BaseVersionModel)t).setUpdt_by("");
            ((BaseVersionModel)t).setUpdt_dtetme(currentDateTime);
        }
        List<TableColumnMapping> pks = t.pks(true);
        if(pks.size() == 1){
            TableColumnMapping pk = pks.get(0);
            if(StringUtils.isNotEmpty(pk.getSequence())){
                int nextSequence = ApplicationContextHolder.get().getBean("sqlServerSequenceGenerator",SqlServerSequenceGenerator.class).getNext(pk.getSequence());
                BeanUtil.setObjPropertyValue(t, pk.getObjectField(), nextSequence);
            }
        }

        try {
            mapper.insert(t);
        } catch (DuplicateKeyException e) {
            throw new DuplicateInsertException();
        }
    }

    public void update(T t) {
        if(t == null) {
            throw new NullArgumentException("Model TO");
        }

        if (t instanceof BaseVersionModel) {
            ((BaseVersionModel)t).setUpdt_by("");
            ((BaseVersionModel)t).setUpdt_dtetme(DateUtil.currentDateTime());
        }
        if (t instanceof BaseEntityModel){
        	t.setAmendList(((BaseEntityModel) t).getAmendListWithTabName());
        }
        int result = mapper.update(t);
        if(result == 0){
            throw new ConcurrencyUpdateException();
        }
    }

    public void delete(T t) {
        if(t == null) {
            throw new NullArgumentException("Model TO");
        }
        mapper.delete(t);
    }

    public T queryObject(T t) {
        if(t == null) {
            throw new NullArgumentException("Model TO");
        }

        return mapper.queryObject(t);
    }

    public T queryObjectWithoutCache(T t) {
        if(t == null) {
            throw new NullArgumentException("Model TO");
        }

        return mapper.queryObjectWithoutCache(t);
    }

    public T queryObjectByUniqueKey(T t) {
        if(t == null) {
            throw new NullArgumentException("Model TO");
        }

        List<T> objectList = mapper.queryList(t);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }
        return null;
    }

    public List<T> queryList(T t) {
        if(t == null) {
            throw new NullArgumentException("Model TO");
        }

        return mapper.queryList(t);
    }

    public List<T> queryAll(Class<T> clazz) {
        if(clazz == null) {
            throw new NullArgumentException("Class");
        }

        return mapper.queryAll(clazz);
    }
}
