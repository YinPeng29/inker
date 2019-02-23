package cc.mrbird.inker.service.bannerinfo.impl;

import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.inker.dao.BannerInfoMapper;
import cc.mrbird.inker.domain.BannerInfo;
import cc.mrbird.inker.service.bannerinfo.BannerInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BannerInfoServiceImpl
 * @Description banner服务实现类
 * @author wuhan
 * @date 2019-02-19 17:45
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class BannerInfoServiceImpl extends BaseService<BannerInfo> implements BannerInfoService {

    private Logger log  = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BannerInfoMapper bannerInfoMapper;

    @Override
    public List<BannerInfo> selectBannerInfoWithParams(BannerInfo bannerInfo, QueryRequest request) {

        try {
        Example example = new Example(BannerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(bannerInfo.getTitle())){
            criteria.andLike("title","%"+bannerInfo.getTitle()+"%");
        }
        example.setOrderByClause("create_date desc");
        return bannerInfoMapper.selectByExample(example);

        }catch (Exception e){
            log.error("获取轮播数据出错",e);
            return new ArrayList<>();
        }
    }
}
