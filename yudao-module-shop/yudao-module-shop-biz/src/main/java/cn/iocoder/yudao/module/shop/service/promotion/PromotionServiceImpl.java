package cn.iocoder.yudao.module.shop.service.promotion;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.PromotionCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.PromotionExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.PromotionPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.PromotionUpdateReqVO;
import cn.iocoder.yudao.module.shop.convert.promotion.PromotionConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.promotion.PromotionDO;
import cn.iocoder.yudao.module.shop.dal.mysql.promotion.PromotionMapper;
import cn.iocoder.yudao.module.shop.enums.PromotionTypeEnum;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.PROMOTION_NOT_EXISTS;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.PROMOTION_PRODUCT_EXISTED;

/**
 * 促销活动 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class PromotionServiceImpl implements PromotionService {

    @Resource
    private PromotionMapper promotionMapper;

    @Override
    public Long createPromotion(PromotionCreateReqVO createReqVO) {
        if (PromotionTypeEnum.AMOUNT.getType().equals(createReqVO.getPromotionType())) {
            // 同一产品仅支持一个买送活动
            PromotionDO one = promotionMapper.selectOne(PromotionDO::getProductId, createReqVO.getProductId());
            if (one != null){
                throw exception(PROMOTION_PRODUCT_EXISTED);
            }
        }
        // 插入
        PromotionDO promotion = PromotionConvert.INSTANCE.convert(createReqVO);
        promotionMapper.insert(promotion);
        // 返回
        return promotion.getId();
    }

    @Override
    public void updatePromotion(PromotionUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatePromotionExists(updateReqVO.getId());
        // 更新
        PromotionDO updateObj = PromotionConvert.INSTANCE.convert(updateReqVO);
        promotionMapper.updateById(updateObj);
    }

    @Override
    public void deletePromotion(Long id) {
        // 校验存在
        this.validatePromotionExists(id);
        // 删除
        promotionMapper.deleteById(id);
    }

    private void validatePromotionExists(Long id) {
        if (promotionMapper.selectById(id) == null) {
            throw exception(PROMOTION_NOT_EXISTS);
        }
    }

    @Override
    public PromotionDO getPromotion(Long id) {
        return promotionMapper.selectById(id);
    }

    @Override
    public List<PromotionDO> getPromotionList(Collection<Long> ids) {
        return promotionMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PromotionDO> getPromotionPage(PromotionPageReqVO pageReqVO) {
        return promotionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PromotionDO> getPromotionList(PromotionExportReqVO exportReqVO) {
        return promotionMapper.selectList(exportReqVO);
    }

    @Override
    public List<PromotionDO> getPromotionListByProductIds(List<Long> ids) {
        return promotionMapper.selectListByProductIds(ids);
    }

}
