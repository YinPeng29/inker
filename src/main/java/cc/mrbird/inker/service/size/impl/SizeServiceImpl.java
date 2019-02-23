package cc.mrbird.inker.service.size.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.inker.dao.SizeMapper;
import cc.mrbird.inker.domain.Size;
import cc.mrbird.inker.service.size.SizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/** 尺码服务实现类
 * @ClassName SizeServiceImpl
 * @Description 尺码服务实现类
 * @author wuhan
 * @date 2019-01-22 18:03
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class SizeServiceImpl extends BaseService<Size> implements SizeService {
    private Logger logger = LoggerFactory.getLogger(SizeServiceImpl.class);

    @Autowired
    private SizeMapper sizeMapper;

    @Override
    public List<Size> selectSizeWithParams(Size size, QueryRequest request) {

        return sizeMapper.selectAll();
    }

    @Override
    public void addSize(Size size) {
        this.sizeMapper.insert(size);
    }

    @Override
    public Size findById(String id) {
        return sizeMapper.queryById(id);
    }

    @Override
    public void batchDeleteSize(List<String> ids) {
        this.batchDelete(ids,"id", Size.class);
    }
}
