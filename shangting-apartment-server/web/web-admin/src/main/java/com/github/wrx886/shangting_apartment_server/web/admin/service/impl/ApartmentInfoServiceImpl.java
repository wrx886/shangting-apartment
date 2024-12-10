package com.github.wrx886.shangting_apartment_server.web.admin.service.impl;

import com.github.wrx886.shangting_apartment_server.common.result.ApartmentException;
import com.github.wrx886.shangting_apartment_server.common.result.ResultCodeEnum;
import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentFacility;
import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentFeeValue;
import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentLabel;
import com.github.wrx886.shangting_apartment_server.model.entity.FacilityInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.GraphInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.LabelInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomInfo;
import com.github.wrx886.shangting_apartment_server.model.enums.ItemType;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.ApartmentInfoMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.FacilityInfoMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.FeeValueMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.GraphInfoMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.LabelInfoMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.service.ApartmentFacilityService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.ApartmentFeeValueService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.ApartmentInfoService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.ApartmentLabelService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.GraphInfoService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomInfoService;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentDetailVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentQueryVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentSubmitVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.fee.FeeValueVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.graph.GraphVo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    @Autowired
    private GraphInfoService graphInfoService;

    @Autowired
    private ApartmentFacilityService apartmentFacilityService;

    @Autowired
    private ApartmentLabelService apartmentLabelService;

    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    private GraphInfoMapper graphInfoMapper;

    @Autowired
    private FacilityInfoMapper facilityInfoMapper;

    @Autowired
    private LabelInfoMapper labelInfoMapper;

    @Autowired
    private FeeValueMapper feeValueMapper;

    @Autowired
    private RoomInfoService roomInfoService;

    // 保存或更新公寓信息
    @Override
    public void saveOrUpdateApartmentSubmitVo(ApartmentSubmitVo apartmentSubmitVo) {
        // 插入或更新公寓信息
        saveOrUpdate(apartmentSubmitVo);
        // 图片信息修改（先删除后插入）
        graphInfoService.remove(new LambdaQueryWrapper<GraphInfo>()
                .eq(GraphInfo::getItemType, ItemType.APARTMENT)
                .eq(GraphInfo::getItemId, apartmentSubmitVo.getId()));
        if (!CollectionUtils.isEmpty(apartmentSubmitVo.getGraphVoList())) {
            graphInfoService.saveBatch(apartmentSubmitVo.getGraphVoList().stream()
                    .map(new Function<GraphVo, GraphInfo>() {
                        @Override
                        public GraphInfo apply(GraphVo graphVo) {
                            GraphInfo graphInfo = new GraphInfo();
                            graphInfo.setItemType(ItemType.APARTMENT);
                            graphInfo.setItemId(apartmentSubmitVo.getId());
                            graphInfo.setName(graphVo.getName());
                            graphInfo.setUrl(graphVo.getUrl());
                            return graphInfo;
                        }
                    })
                    .collect(Collectors.toList()));
        }
        // 配套信息修改（先删除后插入）
        apartmentFacilityService.remove(new LambdaQueryWrapper<ApartmentFacility>()
                .eq(ApartmentFacility::getApartmentId, apartmentSubmitVo.getId()));
        if (!CollectionUtils.isEmpty(apartmentSubmitVo.getFacilityInfoIds())) {
            apartmentFacilityService.saveBatch(apartmentSubmitVo.getFacilityInfoIds().stream()
                    .map(new Function<Long, ApartmentFacility>() {
                        @Override
                        public ApartmentFacility apply(Long facilityId) {
                            ApartmentFacility apartmentFacility = new ApartmentFacility();
                            apartmentFacility.setApartmentId(apartmentSubmitVo.getId());
                            apartmentFacility.setFacilityId(facilityId);
                            return apartmentFacility;
                        }
                    })
                    .collect(Collectors.toList()));
        }
        // 标签列表的修改
        apartmentLabelService.remove(new LambdaQueryWrapper<ApartmentLabel>()
                .eq(ApartmentLabel::getApartmentId, apartmentSubmitVo.getId()));
        if (!CollectionUtils.isEmpty(apartmentSubmitVo.getLabelIds())) {
            apartmentLabelService.saveBatch(apartmentSubmitVo.getLabelIds().stream()
                    .map(new Function<Long, ApartmentLabel>() {
                        @Override
                        public ApartmentLabel apply(Long labelId) {
                            ApartmentLabel apartmentLabel = new ApartmentLabel();
                            apartmentLabel.setId(labelId);
                            apartmentLabel.setApartmentId(apartmentSubmitVo.getId());
                            return apartmentLabel;
                        }
                    })
                    .collect(Collectors.toList()));
        }
        // 杂费列表修改
        apartmentFeeValueService.remove(new LambdaQueryWrapper<ApartmentFeeValue>()
                .eq(ApartmentFeeValue::getApartmentId, apartmentSubmitVo.getId()));
        if (!CollectionUtils.isEmpty(apartmentSubmitVo.getFeeValueIds())) {
            apartmentFeeValueService.saveBatch(apartmentSubmitVo.getFeeValueIds().stream()
                    .map(new Function<Long, ApartmentFeeValue>() {
                        @Override
                        public ApartmentFeeValue apply(Long t) {
                            ApartmentFeeValue apartmentFeeValue = new ApartmentFeeValue();
                            apartmentFeeValue.setApartmentId(apartmentSubmitVo.getId());
                            apartmentFeeValue.setFeeValueId(t);
                            return apartmentFeeValue;
                        }
                    })
                    .collect(Collectors.toList()));
        }
    }

    // 根据条件分页查询公寓列表
    @Override
    public Page<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        return apartmentInfoMapper.pageItem(page, queryVo);
    }

    // 根据ID获取公寓详细信息
    @Override
    public ApartmentDetailVo getDetailById(Long id) {
        // 获取公寓信息
        ApartmentInfo apartmentInfo = getById(id);
        if (apartmentInfo == null) {
            return new ApartmentDetailVo();
        }
        // 查询图片列表
        List<GraphVo> graphInfoList = graphInfoMapper.selectListByItemTypeAndId(id, ItemType.APARTMENT);
        // 查询标签列表
        List<LabelInfo> labelInfoList = labelInfoMapper.selectListByApartmentId(id);
        // 查询配套列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.selectListByApartmentId(id);
        // 查询杂费列表
        List<FeeValueVo> feeValueVoList = feeValueMapper.selectListByApartmentId(id);
        // 整合
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphInfoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setFeeValueVoList(feeValueVoList);
        // 返回
        return apartmentDetailVo;
    }

    // 根据 id 删除公寓
    @Override
    public void removeApartmentById(Long id) {
        // 判断公寓是否含有房间
        if (roomInfoService.count(new LambdaQueryWrapper<RoomInfo>()
                .eq(RoomInfo::getApartmentId, id)) > 0) {
            // 这里抛出一个异常，表示要先删除房间
            throw new ApartmentException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }
        // 删除公寓信息
        removeById(id);
        // 删除图片信息
        graphInfoService.remove(new LambdaQueryWrapper<GraphInfo>()
                .eq(GraphInfo::getItemType, ItemType.APARTMENT)
                .eq(GraphInfo::getItemId, id));
        // 删除配套信息
        apartmentFacilityService.remove(new LambdaQueryWrapper<ApartmentFacility>()
                .eq(ApartmentFacility::getApartmentId, id));
        // 删除标签信息
        apartmentLabelService.remove(new LambdaQueryWrapper<ApartmentLabel>()
                .eq(ApartmentLabel::getApartmentId, id));
        // 删除杂费信息
        apartmentFeeValueService.remove(new LambdaQueryWrapper<ApartmentFeeValue>()
                .eq(ApartmentFeeValue::getApartmentId, id));
    }
}
