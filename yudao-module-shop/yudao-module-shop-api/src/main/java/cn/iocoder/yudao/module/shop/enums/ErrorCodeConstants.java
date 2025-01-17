package cn.iocoder.yudao.module.shop.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 工作流 错误码枚举类
 *
 * 工作流系统，使用 1-009-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 门店 TODO 补充编号 ==========
    ErrorCode BRANCH_NOT_EXISTS = new ErrorCode(1022001001, "门店不存在");

    // ========== 商品品牌 TODO 补充编号 ==========
    ErrorCode BRAND_NOT_EXISTS = new ErrorCode(1022002001, "商品品牌不存在");


    // ========== 会员 TODO 补充编号 ==========
    ErrorCode MEMBER_NOT_EXISTS = new ErrorCode(1022003001, "会员不存在");
    ErrorCode MEMBER_BALANCE_NOT_ENOUGH = new ErrorCode(1022003002, "会员余额不足");

    // ========== 商品分类 TODO 补充编号 ==========
    ErrorCode CATEGORY_NOT_EXISTS = new ErrorCode(1022004001, "商品分类不存在");

    // ========== 会员充值套餐 TODO 补充编号 ==========
    ErrorCode RECHARGE_NOT_EXISTS = new ErrorCode(1022005001, "充值活动不存在");

    // ========== 充值订单 TODO 补充编号 ==========
    ErrorCode RECHARGE_ORDER_NOT_EXISTS = new ErrorCode(1022006001, "充值订单不存在");

    ErrorCode MEMBER_ACCOUNT_LOG_NOT_EXISTS = new ErrorCode(1022007001, "会员账户流水不存在");

    ErrorCode PRODUCT_NOT_EXISTS = new ErrorCode(1022008001, "产品不存在");

    ErrorCode BRANCH_GOODS_NOT_EXISTS = new ErrorCode(1022009001, "商品不存在:{}");
    ErrorCode BRANCH_GOODS_NOT_ENOUGH = new ErrorCode(1022009002, "商品库存不足:{}");
    ErrorCode BRANCH_GOODS_UPDATE_FAILED = new ErrorCode(1022009003, "商品库存更新失败:{}");

    ErrorCode BRANCH_STOCK_NOT_EXISTS = new ErrorCode(1022010001, "出入库表单不存在");

    ErrorCode BRANCH_STOCK_ITEM_NOT_EXISTS = new ErrorCode(1022010002, "出入库明细不存在");

    // ========== 门店订单 1022011001 ==========
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(1022011001, "门店订单不存在");
    ErrorCode ORDER_ITEM_NOT_EXISTS = new ErrorCode(1022011002, "门店订单明细不存在");
    ErrorCode ORDER_IS_DONE = new ErrorCode(1022011003, "订单已完成，请查询后再试！");
    ErrorCode ORDER_PAID_LG_PRICE = new ErrorCode(1022011004, "订单支付金额大于订单金额！");
    ErrorCode ORDER_PAID_FAILED = new ErrorCode(1022011005, "订单[{}]支付状态异常，请联系管理员！");
    ErrorCode ORDER_CHANGE_FAILED = new ErrorCode(1022011006, "订单更改失败，只允许未支付的订单更改订单类型！");

    // ========== 促销活动 1022012001 ==========
    ErrorCode PROMOTION_NOT_EXISTS = new ErrorCode(1022012001, "促销活动不存在");
    ErrorCode PROMOTION_PRODUCT_EXISTED = new ErrorCode(1022012002, "该产品已存在促销活动！");
    ErrorCode PROMOTION_STATUS_FALSE = new ErrorCode(1022012003, "促销活动已失效");



}
