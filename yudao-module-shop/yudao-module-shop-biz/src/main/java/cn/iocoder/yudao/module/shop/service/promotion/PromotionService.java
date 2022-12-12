package cn.iocoder.yudao.module.shop.service.promotion;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.promotion.PromotionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 促销活动 Service 接口
 *
 * @author ruanzh
 */
public interface PromotionService {

    /**
     * 创建促销活动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPromotion(@Valid PromotionCreateReqVO createReqVO);

    /**
     * 更新促销活动
     *
     * @param updateReqVO 更新信息
     */
    void updatePromotion(@Valid PromotionUpdateReqVO updateReqVO);

    /**
     * 删除促销活动
     *
     * @param id 编号
     */
    void deletePromotion(Long id);

    /**
     * 获得促销活动
     *
     * @param id 编号
     * @return 促销活动
     */
    PromotionDO getPromotion(Long id);

    /**
     * 获得促销活动列表
     *
     * @param ids 编号
     * @return 促销活动列表
     */
    List<PromotionDO> getPromotionList(Collection<Long> ids);

    /**
     * 获得促销活动分页
     *
     * @param pageReqVO 分页查询
     * @return 促销活动分页
     */
    PageResult<PromotionDO> getPromotionPage(PromotionPageReqVO pageReqVO);

    /**
     * 获得促销活动列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 促销活动列表
     */
    List<PromotionDO> getPromotionList(PromotionExportReqVO exportReqVO);

}
