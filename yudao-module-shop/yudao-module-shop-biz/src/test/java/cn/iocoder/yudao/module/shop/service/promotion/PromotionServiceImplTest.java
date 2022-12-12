package cn.iocoder.yudao.module.shop.service.promotion;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.promotion.PromotionDO;
import cn.iocoder.yudao.module.shop.dal.mysql.promotion.PromotionMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link PromotionServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(PromotionServiceImpl.class)
public class PromotionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private PromotionServiceImpl promotionService;

    @Resource
    private PromotionMapper promotionMapper;

    @Test
    public void testCreatePromotion_success() {
        // 准备参数
        PromotionCreateReqVO reqVO = randomPojo(PromotionCreateReqVO.class);

        // 调用
        Long promotionId = promotionService.createPromotion(reqVO);
        // 断言
        assertNotNull(promotionId);
        // 校验记录的属性是否正确
        PromotionDO promotion = promotionMapper.selectById(promotionId);
        assertPojoEquals(reqVO, promotion);
    }

    @Test
    public void testUpdatePromotion_success() {
        // mock 数据
        PromotionDO dbPromotion = randomPojo(PromotionDO.class);
        promotionMapper.insert(dbPromotion);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PromotionUpdateReqVO reqVO = randomPojo(PromotionUpdateReqVO.class, o -> {
            o.setId(dbPromotion.getId()); // 设置更新的 ID
        });

        // 调用
        promotionService.updatePromotion(reqVO);
        // 校验是否更新正确
        PromotionDO promotion = promotionMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, promotion);
    }

    @Test
    public void testUpdatePromotion_notExists() {
        // 准备参数
        PromotionUpdateReqVO reqVO = randomPojo(PromotionUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> promotionService.updatePromotion(reqVO), PROMOTION_NOT_EXISTS);
    }

    @Test
    public void testDeletePromotion_success() {
        // mock 数据
        PromotionDO dbPromotion = randomPojo(PromotionDO.class);
        promotionMapper.insert(dbPromotion);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbPromotion.getId();

        // 调用
        promotionService.deletePromotion(id);
       // 校验数据不存在了
       assertNull(promotionMapper.selectById(id));
    }

    @Test
    public void testDeletePromotion_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> promotionService.deletePromotion(id), PROMOTION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPromotionPage() {
       // mock 数据
       PromotionDO dbPromotion = randomPojo(PromotionDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPromotionType(null);
           o.setStatus(null);
       });
       promotionMapper.insert(dbPromotion);
       // 测试 name 不匹配
       promotionMapper.insert(cloneIgnoreId(dbPromotion, o -> o.setName(null)));
       // 测试 promotionType 不匹配
       promotionMapper.insert(cloneIgnoreId(dbPromotion, o -> o.setPromotionType(null)));
       // 测试 status 不匹配
       promotionMapper.insert(cloneIgnoreId(dbPromotion, o -> o.setStatus(null)));
       // 准备参数
       PromotionPageReqVO reqVO = new PromotionPageReqVO();
       reqVO.setName(null);
       reqVO.setPromotionType(null);
       reqVO.setStatus(null);

       // 调用
       PageResult<PromotionDO> pageResult = promotionService.getPromotionPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbPromotion, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetPromotionList() {
       // mock 数据
       PromotionDO dbPromotion = randomPojo(PromotionDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPromotionType(null);
           o.setStatus(null);
       });
       promotionMapper.insert(dbPromotion);
       // 测试 name 不匹配
       promotionMapper.insert(cloneIgnoreId(dbPromotion, o -> o.setName(null)));
       // 测试 promotionType 不匹配
       promotionMapper.insert(cloneIgnoreId(dbPromotion, o -> o.setPromotionType(null)));
       // 测试 status 不匹配
       promotionMapper.insert(cloneIgnoreId(dbPromotion, o -> o.setStatus(null)));
       // 准备参数
       PromotionExportReqVO reqVO = new PromotionExportReqVO();
       reqVO.setName(null);
       reqVO.setPromotionType(null);
       reqVO.setStatus(null);

       // 调用
       List<PromotionDO> list = promotionService.getPromotionList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbPromotion, list.get(0));
    }

}
