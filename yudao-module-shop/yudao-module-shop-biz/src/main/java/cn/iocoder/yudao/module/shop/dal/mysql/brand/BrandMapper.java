package cn.iocoder.yudao.module.shop.dal.mysql.brand;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.brand.BrandDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.brand.vo.*;

/**
 * 商品品牌 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface BrandMapper extends BaseMapperX<BrandDO> {

    default PageResult<BrandDO> selectPage(BrandPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BrandDO>()
                .likeIfPresent(BrandDO::getName, reqVO.getName())
                .betweenIfPresent(BrandDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BrandDO::getId));
    }

    default List<BrandDO> selectList(BrandExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BrandDO>()
                .likeIfPresent(BrandDO::getName, reqVO.getName())
                .betweenIfPresent(BrandDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BrandDO::getId));
    }

}
