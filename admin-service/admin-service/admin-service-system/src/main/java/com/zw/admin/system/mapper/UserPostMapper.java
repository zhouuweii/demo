package com.zw.admin.system.mapper;

import com.zw.admin.framework.domain.entity.SysUserPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与岗位关联表
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Mapper
public interface UserPostMapper {

    /**
     * 通过岗位ID查询岗位使用数量
     * @param postId 岗位ID
     * @return 结果
     */
    public int countUserPostById(Long postId);

    /**
     * 批量新增用户岗位信息
     * @param userPostList 用户角色列表
     * @return 结果
     */
    public int batchUserPost(List<SysUserPost> userPostList);

    /**
     * 通过用户ID删除用户和岗位关联
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserPostByUserId(Long userId);

    /**
     * 批量删除用户和岗位关联
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserPost(Long[] ids);

}
