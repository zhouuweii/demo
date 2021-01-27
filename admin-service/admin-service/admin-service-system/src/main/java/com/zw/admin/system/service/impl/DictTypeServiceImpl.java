package com.zw.admin.system.service.impl;

import com.zw.admin.framework.common.constant.CacheConstants;
import com.zw.admin.framework.common.constant.Constants;
import com.zw.admin.framework.common.exception.ExceptionCast;
import com.zw.admin.framework.common.response.CommonCode;
import com.zw.admin.framework.common.utils.StringUtils;
import com.zw.admin.framework.core.service.RedisTemplateUtil;
import com.zw.admin.framework.domain.entity.SysDictData;
import com.zw.admin.framework.domain.entity.SysDictType;
import com.zw.admin.system.mapper.DictDataMapper;
import com.zw.admin.system.mapper.DictTypeMapper;
import com.zw.admin.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

/**
 * 字典类型
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictDataMapper dictDataMapper;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
        for (SysDictType dictType : dictTypeList) {
            List<SysDictData> dictDataList = dictDataMapper.selectDictDataByType(dictType.getDictType());
            redisTemplateUtil.setCacheObject(CacheConstants.SYS_DICT_KEY + dictType.getDictType(),
                    dictDataList, CacheConstants.SYS_DICT_KEY_TIME);
        }
    }

    /**
     * 根据条件分页查询字典类型
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 获取所有字典类型
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型ID查询信息
     * @param dictId 字典类型ID
     * @return 字典类型信息
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 根据字典类型查询字典数据
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> dictDataList = redisTemplateUtil.getCacheObject(CacheConstants.SYS_DICT_KEY + dictType);
        if (StringUtils.isNotEmpty(dictDataList)) {
            return dictDataList;
        }
        dictDataList = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDataList)) {
            redisTemplateUtil.setCacheObject(CacheConstants.SYS_DICT_KEY + dictType, dictDataList, CacheConstants.SYS_DICT_KEY_TIME);
            return dictDataList;
        }
        return null;
    }

    /**
     * 新增保存字典类型信息
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType) {
        int row = dictTypeMapper.insertDictType(dictType);
        if (row > 0) {
            clearCache();
        }
        return row;
    }

    /**
     * 修改字典类型信息
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateDictType(SysDictType dictType) {
        SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType());
        int row = dictTypeMapper.updateDictType(dictType);
        if (row > 0) {
            clearCache();
        }
        return row;
    }

    /**
     * 批量删除字典类型信息
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
                ExceptionCast.cast(CommonCode.DATA_NO_DELETE);
            }
        }
        int count = dictTypeMapper.deleteDictTypeByIds(dictIds);
        if (count > 0) {
            clearCache();
        }
        return count;
    }

    /**
     * 清空缓存数据
     */
    @Override
    public void clearCache() {
        Collection<String> keys = redisTemplateUtil.keys(Constants.SYS_DICT_KEY + "*");
        redisTemplateUtil.deleteObject(keys);
    }

    /**
     * 校验字典类型称是否唯一
     * @param dict 字典类型
     * @return boolean true:唯一  false：不唯一
     **/
    @Override
    public Boolean checkDictTypeUnique(SysDictType dict) {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
