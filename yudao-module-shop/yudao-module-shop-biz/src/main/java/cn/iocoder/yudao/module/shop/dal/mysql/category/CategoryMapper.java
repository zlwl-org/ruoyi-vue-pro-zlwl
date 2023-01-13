package cn.iocoder.yudao.module.shop.dal.mysql.category;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.category.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.category.vo.*;

/**
 * 商品分类 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

    default PageResult<CategoryDO> selectPage(CategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CategoryDO>()
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus())
                .orderByDesc(CategoryDO::getId));
    }

    default List<CategoryDO> selectList(CategoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus())
                .orderByDesc(CategoryDO::getId));
    }

}
