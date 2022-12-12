package cn.iocoder.yudao.module.shop.service.branch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.convert.branch.BranchStockConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.product.ProductDO;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchStockMapper;
import cn.iocoder.yudao.module.shop.enums.StockTypeEnum;
import cn.iocoder.yudao.module.shop.service.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 门店出入库 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
@Transactional
public class BranchStockServiceImpl implements BranchStockService {

    @Resource
    private BranchStockMapper branchStockMapper;

    @Resource
    private BranchStockItemService itemService;

    @Resource
    private BranchGoodsService goodsService;

    @Resource
    private ProductService productService;

    @Override
    public Long createBranchStock(BranchStockCreateReqVO createReqVO) {
        // 插入
        BranchStockDO branchStock = BranchStockConvert.INSTANCE.convert(createReqVO);
        branchStockMapper.insert(branchStock);
        // 返回
        // 插入item
        List<BranchStockItemCreateReqVO> list = createReqVO.getList();
        list.forEach(item -> {
            item.setBranchId(createReqVO.getBranchId());
            item.setStockId(branchStock.getId());
            itemService.createBranchStockItem(item);

            BranchGoodsDO good = goodsService.getByBranchIdAndProductId(createReqVO.getBranchId(), item.getProductId());
            // 出库
            if (StockTypeEnum.OUT.getType().equals(item.getType())) {
                if (good == null){
                    throw exception(BRANCH_GOODS_NOT_EXISTS, item.getProductName());
                }
                if(good.getStock() - item.getAmount() < 0){
                    throw exception(BRANCH_GOODS_NOT_ENOUGH, item.getProductName());
                }
                int result = goodsService.updateGoodStock(good.getId(), -item.getAmount());
                if (result == 0){
                    throw exception(BRANCH_GOODS_NOT_ENOUGH, item.getProductName());
                }
            } else { // 入库
                if (good != null){
                    goodsService.updateGoodStock(good.getId(), item.getAmount());
                } else {
                    ProductDO product = productService.getProduct(item.getProductId());
                    if (product == null) {
                        throw exception(PRODUCT_NOT_EXISTS);
                    }

                    BranchGoodsCreateReqVO goodVO = new BranchGoodsCreateReqVO();
                    goodVO.setName(product.getName());
                    goodVO.setPrice(product.getPrice());
                    goodVO.setProductId(product.getId());
                    goodVO.setBranchId(createReqVO.getBranchId());
                    goodVO.setStock(item.getAmount());
                    goodsService.createBranchGoods(goodVO);
                }
            }

        });
        return branchStock.getId();
    }

    @Override
    public void updateBranchStock(BranchStockUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBranchStockExists(updateReqVO.getId());
        // 更新
        BranchStockDO updateObj = BranchStockConvert.INSTANCE.convert(updateReqVO);
        branchStockMapper.updateById(updateObj);
    }

    @Override
    public void deleteBranchStock(Long id) {
        // 校验存在
        this.validateBranchStockExists(id);
        // 删除
        branchStockMapper.deleteById(id);
    }

    private void validateBranchStockExists(Long id) {
        if (branchStockMapper.selectById(id) == null) {
            throw exception(BRANCH_STOCK_NOT_EXISTS);
        }
    }

    @Override
    public BranchStockDO getBranchStock(Long id) {
        return branchStockMapper.selectById(id);
    }

    @Override
    public List<BranchStockDO> getBranchStockList(Collection<Long> ids) {
        return branchStockMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BranchStockDO> getBranchStockPage(BranchStockPageReqVO pageReqVO) {
        return branchStockMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BranchStockDO> getBranchStockList(BranchStockExportReqVO exportReqVO) {
        return branchStockMapper.selectList(exportReqVO);
    }

}
