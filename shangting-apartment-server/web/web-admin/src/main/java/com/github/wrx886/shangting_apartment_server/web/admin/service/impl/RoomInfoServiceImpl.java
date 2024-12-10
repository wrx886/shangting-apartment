package com.github.wrx886.shangting_apartment_server.web.admin.service.impl;

import com.github.wrx886.shangting_apartment_server.model.entity.FacilityInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.GraphInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.LabelInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.LeaseTerm;
import com.github.wrx886.shangting_apartment_server.model.entity.PaymentType;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomAttrValue;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomFacility;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomLabel;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomLeaseTerm;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomPaymentType;
import com.github.wrx886.shangting_apartment_server.model.enums.ItemType;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.GraphInfoMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.RoomAttrValueMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.RoomFacilityMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.RoomInfoMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.RoomLabelMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.RoomLeaseTermMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.RoomPaymentTypeMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.service.GraphInfoService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomAttrValueService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomFacilityService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomInfoService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomLabelService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomLeaseTermService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomPaymentTypeService;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.attr.AttrValueVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.graph.GraphVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomDetailVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomQueryVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomSubmitVo;
import com.google.common.graph.Graph;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Graphiql;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

    @Autowired
    private GraphInfoService graphInfoService;

    @Autowired
    private RoomAttrValueService roomAttrValueService;

    @Autowired
    private RoomFacilityService roomFacilityService;

    @Autowired
    private RoomLabelService roomLabelService;

    @Autowired
    private RoomPaymentTypeService roomPaymentTypeService;

    @Autowired
    private RoomLeaseTermService roomLeaseTermService;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private GraphInfoMapper graphInfoMapper;

    @Autowired
    private RoomAttrValueMapper roomAttrValueMapper;

    @Autowired
    private RoomFacilityMapper roomFacilityMapper;

    @Autowired
    private RoomLabelMapper roomLabelMapper;

    @Autowired
    private RoomPaymentTypeMapper roomPaymentTypeMapper;

    @Autowired
    private RoomLeaseTermMapper roomLeaseTermMapper;

    // 保存或更新房间信息
    @Override
    public void savaOrUpdateRoomSubmitVo(RoomSubmitVo roomSubmitVo) {
        // 更新房间信息
        saveOrUpdate(roomSubmitVo);
        // 更新图片列表（先删除后插入）
        graphInfoService.remove(new LambdaQueryWrapper<GraphInfo>()
                .eq(GraphInfo::getItemId, roomSubmitVo.getId())
                .eq(GraphInfo::getItemType, ItemType.ROOM));
        graphInfoService.saveBatch(roomSubmitVo.getGraphVoList().stream()
                .map(new Function<GraphVo, GraphInfo>() {
                    @Override
                    public GraphInfo apply(GraphVo graphVo) {
                        GraphInfo graphInfo = new GraphInfo();
                        graphInfo.setName(graphInfo.getName());
                        graphInfo.setItemId(roomSubmitVo.getId());
                        graphInfo.setItemType(ItemType.ROOM);
                        graphInfo.setUrl(graphInfo.getUrl());
                        return graphInfo;
                    }
                }).toList());
        // 更新属性信息列表（先删除后插入）
        roomAttrValueService.remove(new LambdaQueryWrapper<RoomAttrValue>()
                .eq(RoomAttrValue::getRoomId, roomSubmitVo.getId()));
        roomAttrValueService.saveBatch(roomSubmitVo.getAttrValueIds().stream()
                .map(new Function<Long, RoomAttrValue>() {
                    @Override
                    public RoomAttrValue apply(Long roomAttrId) {
                        RoomAttrValue roomAttrValue = new RoomAttrValue();
                        roomAttrValue.setAttrValueId(roomAttrId);
                        roomAttrValue.setRoomId(roomSubmitVo.getId());
                        return roomAttrValue;
                    }
                }).toList());
        // 更新配套信息列表（先删除后插入）
        roomFacilityService.remove(new LambdaQueryWrapper<RoomFacility>()
                .eq(RoomFacility::getRoomId, roomSubmitVo.getId()));
        roomFacilityService.saveBatch(roomSubmitVo.getFacilityInfoIds().stream()
                .map(new Function<Long, RoomFacility>() {
                    @Override
                    public RoomFacility apply(Long facilityId) {
                        RoomFacility roomFacility = new RoomFacility();
                        roomFacility.setFacilityId(facilityId);
                        roomFacility.setRoomId(roomFacility.getId());
                        return roomFacility;
                    }
                }).toList());
        // 更新标签信息（先删除后插入）
        roomLabelService.remove(new LambdaQueryWrapper<RoomLabel>()
                .eq(RoomLabel::getRoomId, roomSubmitVo.getId()));
        roomLabelService.saveBatch(roomSubmitVo.getLabelInfoIds().stream()
                .map(new Function<Long, RoomLabel>() {
                    @Override
                    public RoomLabel apply(Long labelId) {
                        RoomLabel roomLabel = new RoomLabel();
                        roomLabel.setLabelId(labelId);
                        roomLabel.setRoomId(roomSubmitVo.getId());
                        return roomLabel;
                    }
                }).toList());
        // 更新支付方式列表（先删除后插入）
        roomPaymentTypeService.remove(new LambdaQueryWrapper<RoomPaymentType>()
                .eq(RoomPaymentType::getRoomId, roomSubmitVo.getId()));
        roomPaymentTypeService.saveBatch(roomSubmitVo.getPaymentTypeIds().stream()
                .map(new Function<Long, RoomPaymentType>() {
                    @Override
                    public RoomPaymentType apply(Long paymentTypeId) {
                        RoomPaymentType roomPaymentType = new RoomPaymentType();
                        roomPaymentType.setRoomId(roomSubmitVo.getId());
                        roomPaymentType.setPaymentTypeId(paymentTypeId);
                        return roomPaymentType;
                    }
                }).toList());
        // 更新可选租期列表（先删除后插入）
        roomLeaseTermService.remove(new LambdaQueryWrapper<RoomLeaseTerm>()
                .eq(RoomLeaseTerm::getRoomId, roomSubmitVo.getId()));
        roomLeaseTermService.saveBatch(roomSubmitVo.getLeaseTermIds().stream()
                .map(new Function<Long, RoomLeaseTerm>() {
                    @Override
                    public RoomLeaseTerm apply(Long leaseTermId) {
                        RoomLeaseTerm roomLeaseTerm = new RoomLeaseTerm();
                        roomLeaseTerm.setRoomId(roomLeaseTerm.getId());
                        roomLeaseTerm.setLeaseTermId(leaseTermId);
                        return roomLeaseTerm;
                    }
                }).toList());
    }

    // 根据条件分页查询房间列表
    @Override
    public IPage<RoomItemVo> pageRoomItemVo(IPage<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageRoomItemVo(page, queryVo);
    }

    // 根据id获取房间详细信息
    @Override
    public RoomDetailVo getDetailById(Long id) {
        // 查询房间信息
        RoomInfo roomInfo = getById(id);
        // 查询图片列表
        List<GraphVo> graphVoList = graphInfoMapper.selectListByItemTypeAndId(id, ItemType.ROOM);
        // 查询属性信息列表
        List<AttrValueVo> attrValueVoList = roomAttrValueMapper.selectByRoomId(id);
        // 查询配套信息列表
        List<FacilityInfo> facilityInfoList = roomFacilityMapper.selectByRoomId(id);
        // 查询标签信息列表
        List<LabelInfo> labelInfoList = roomLabelMapper.selectByRoomId(id);
        // 查询支付方式列表
        List<PaymentType> paymentTypeList = roomPaymentTypeMapper.selectByRoomId(id);
        // 查询可选租期列表
        List<LeaseTerm> leaseTermList = roomLeaseTermMapper.selectByRoomId(id);
        // 封装 RoomDetailVo
        RoomDetailVo roomDetailVo = new RoomDetailVo();
        BeanUtils.copyProperties(roomInfo, roomDetailVo);
        // 复制其他属性
        roomDetailVo.setGraphVoList(graphVoList);
        roomDetailVo.setAttrValueVoList(attrValueVoList);
        roomDetailVo.setFacilityInfoList(facilityInfoList);
        roomDetailVo.setLabelInfoList(labelInfoList);
        roomDetailVo.setPaymentTypeList(paymentTypeList);
        roomDetailVo.setLeaseTermList(leaseTermList);
        // 返回
        return roomDetailVo;
    }

    // 根据id删除房间信息
    @Override
    public void removeByRoomId(Long id) {
        // 删除图片列表
        graphInfoService.remove(new LambdaQueryWrapper<GraphInfo>()
                .eq(GraphInfo::getItemId, id)
                .eq(GraphInfo::getItemType, ItemType.ROOM));
        // 删除属性信息列表
        roomAttrValueService.remove(new LambdaQueryWrapper<RoomAttrValue>()
                .eq(RoomAttrValue::getRoomId, id));

        // 删除配套信息列表
        roomFacilityService.remove(new LambdaQueryWrapper<RoomFacility>()
                .eq(RoomFacility::getRoomId, id));

        // 删除标签信息
        roomLabelService.remove(new LambdaQueryWrapper<RoomLabel>()
                .eq(RoomLabel::getRoomId, id));

        // 删除支付方式列表
        roomPaymentTypeService.remove(new LambdaQueryWrapper<RoomPaymentType>()
                .eq(RoomPaymentType::getRoomId, id));

        // 删除可选租期列表
        roomLeaseTermService.remove(new LambdaQueryWrapper<RoomLeaseTerm>()
                .eq(RoomLeaseTerm::getRoomId, id));

        // 删除房间信息
        removeById(id);
    }
}
