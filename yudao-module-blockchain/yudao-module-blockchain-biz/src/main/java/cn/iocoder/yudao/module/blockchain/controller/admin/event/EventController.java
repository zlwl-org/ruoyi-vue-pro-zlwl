package cn.iocoder.yudao.module.blockchain.controller.admin.event;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.blockchain.controller.admin.event.vo.*;
import cn.iocoder.yudao.module.blockchain.convert.event.EventConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.event.EventDO;
import cn.iocoder.yudao.module.blockchain.service.event.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 事件")
@RestController
@RequestMapping("/blockchain/event")
@Validated
public class EventController {

    @Resource
    private EventService eventService;

    @PostMapping("/create")
    @Operation(summary = "创建事件")
    @PreAuthorize("@ss.hasPermission('blockchain:event:create')")
    public CommonResult<Long> createEvent(@Valid @RequestBody EventCreateReqVO createReqVO) {
        return success(eventService.createEvent(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件")
    @PreAuthorize("@ss.hasPermission('blockchain:event:update')")
    public CommonResult<Boolean> updateEvent(@Valid @RequestBody EventUpdateReqVO updateReqVO) {
        eventService.updateEvent(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件")
//    //@ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:event:delete')")
    public CommonResult<Boolean> deleteEvent(@RequestParam("id") Long id) {
        eventService.deleteEvent(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件")
//    //@ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:event:query')")
    public CommonResult<EventRespVO> getEvent(@RequestParam("id") Long id) {
        EventDO event = eventService.getEvent(id);
        return success(EventConvert.INSTANCE.convert(event));
    }

    @GetMapping("/list")
    @Operation(summary = "获得事件列表")
//    //@ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blockchain:event:query')")
    public CommonResult<List<EventRespVO>> getEventList(@RequestParam("ids") Collection<Long> ids) {
        List<EventDO> list = eventService.getEventList(ids);
        return success(EventConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件分页")
    @PreAuthorize("@ss.hasPermission('blockchain:event:query')")
    public CommonResult<PageResult<EventRespVO>> getEventPage(@Valid EventPageReqVO pageVO) {
        PageResult<EventDO> pageResult = eventService.getEventPage(pageVO);
        return success(EventConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件 Excel")
    @PreAuthorize("@ss.hasPermission('blockchain:event:export')")
    @OperateLog(type = EXPORT)
    public void exportEventExcel(@Valid EventExportReqVO exportReqVO,
                                 HttpServletResponse response) throws IOException {
        List<EventDO> list = eventService.getEventList(exportReqVO);
        // 导出 Excel
        List<EventExcelVO> datas = EventConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "事件.xls", "数据", EventExcelVO.class, datas);
    }

}
