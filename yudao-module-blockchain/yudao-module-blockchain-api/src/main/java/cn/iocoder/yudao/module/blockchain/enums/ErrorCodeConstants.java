package cn.iocoder.yudao.module.blockchain.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * 工作流 错误码枚举类
 *
 * 工作流系统，使用 1-009-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 网络 1023001001 ==========
    ErrorCode NET_NOT_EXISTS = new ErrorCode(1023001001, "网络不存在");

    // ========== 以太坊账户 1023002001 ==========
    ErrorCode ETH_ACCOUNT_NOT_EXISTS = new ErrorCode(1023002001, "以太坊账户不存在");

    // ========== 以太坊主网地址 1023003001 ==========
    ErrorCode ETH_MAIN_NET_ADDRESS_NOT_EXISTS = new ErrorCode(1023003001, "以太坊主网地址不存在");

    // ========== 事件 1023004001 ==========
    ErrorCode EVENT_NOT_EXISTS = new ErrorCode(1023004001, "事件不存在");
}
