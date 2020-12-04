package org.mintleaf.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Mengchu
 * @date : 11:51 2019/10/23
 */
public class MyCasRealm extends CasRealm {
    private static Logger log = LoggerFactory.getLogger(MyCasRealm.class);

    /**
     * 在调用subject.login()时，首先调用此接口
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 调用父类的方法，然后授权用户
        AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
        // 获得用户名
        String username = (String) authc.getPrincipals().getPrimaryPrincipal();
        // TODO:这里应该从数据库中获取用户信息

        return authc;
    }

    /**
     * 进行权限验证的时候，调用方法，将用户的权限信息写进SimpleAuthorizationInfo
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 用户名称
        log.info("进入了权限认证");
        Object username = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO: 这里应该从数据库获取用户权限

        Set<String> permission = new HashSet<>();
        permission.add("sys:dept:list");
        info.setStringPermissions(permission);
        return info;
    }

    /**
     * 重写父类CasRealm的createTicketValidator方法，设置(GBK)编码,解决长江委返回中文乱码的问题
     */
    @Override
    protected TicketValidator createTicketValidator() {
        String urlPrefix = this.getCasServerUrlPrefix();
        Cas20ServiceTicketValidator ticketValidator = new Cas20ServiceTicketValidator(urlPrefix);
        ticketValidator.setEncoding("GBK");
        return ticketValidator;
    }
}
