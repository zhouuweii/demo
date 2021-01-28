package com.zw.admin.auth.config;

import com.zw.admin.auth.domain.UserJwt;
import com.zw.admin.framework.common.response.ResultData;
import com.zw.admin.framework.core.client.SystemClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户详细信息
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Service
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private SystemClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpBasic认证，httpBasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //远程调用用户中心根据账号查询用户信息
        ResultData userExtend = userClient.getInfo(username);
        Map userExt = (Map) userExtend.getData();
        if (userExt == null) {
            System.out.println("用户不存在");
            return null;
        }
        Map<String,Object> sysUser = (Map<String, Object>) userExt.get("sysUser");
        //取出正确密码（hash值）
        String password = (String)sysUser.get("password");
        //从数据库获取用户权限
        List<String> permissions = (List<String>) userExt.get("permissions");
        if(permissions==null){
            permissions =new ArrayList<>();
        }
        String user_permission_string  = StringUtils.join(permissions.toArray(), ",");
        UserJwt userDetails = new UserJwt(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setId(sysUser.get("userId").toString());
        userDetails.setName(sysUser.get("userName").toString());
        userDetails.setUserPicture(sysUser.get("avatar").toString());
        return userDetails;
    }
}
