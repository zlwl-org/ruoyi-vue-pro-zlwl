package cn.iocoder.yudao.module.shop.service.brand;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.shop.controller.admin.brand.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.brand.BrandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 商品品牌 Service 接口
 *
 * @author ZLWL
 */
public interface BrandService {

    /**
     * 创建商品品牌
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBrand(@Valid BrandCreateReqVO createReqVO);

    /**
     * 更新商品品牌
     *
     * @param updateReqVO 更新信息
     */
    void updateBrand(@Valid BrandUpdateReqVO updateReqVO);

    /**
     * 删除商品品牌
     *
     * @param id 编号
     */
    void deleteBrand(Long id);

    /**
     * 获得商品品牌
     *
     * @param id 编号
     * @return 商品品牌
     */
    BrandDO getBrand(Long id);

    /**
     * 获得商品品牌列表
     *
     * @param ids 编号
     * @return 商品品牌列表
     */
    List<BrandDO> getBrandList(Collection<Long> ids);

    /**
     * 获得商品品牌分页
     *
     * @param pageReqVO 分页查询
     * @return 商品品牌分页
     */
    PageResult<BrandDO> getBrandPage(BrandPageReqVO pageReqVO);

    /**
     * 获得商品品牌列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 商品品牌列表
     */
    List<BrandDO> getBrandList(BrandExportReqVO exportReqVO);

}
