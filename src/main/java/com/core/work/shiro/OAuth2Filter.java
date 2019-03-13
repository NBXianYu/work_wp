package com.core.work.shiro;

import com.alibaba.fastjson.JSONObject;
import com.core.work.utils.HttpContextUtils;
import com.core.work.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: oauth2过滤器
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/1/28 0028 下午 16:26
 */
public class OAuth2Filter extends AuthenticatingFilter {

    /**
     * @Description: 第三步调用
     *
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if (StringUtils.isBlank(token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    /***
     * @Description: 第一步：先进入这个方法，拒绝所有，然后会执行onAccessDenied方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        return false;
    }

    /***
     * @Description: 第二步：请求拒绝后执行，先获取token，然后执行executeLogin方法
     *                  点进去这个方法，他执行了subject.login，所以我们定义了一个OAuth2Token类实现AuthenticationToken接口
     *                  然后上面第三步方法createToken 调用
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            String json = JSONObject.toJSONString(Result.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));

            httpResponse.getWriter().print(json);

            return false;
        }

        return executeLogin(request, response);
    }

    /**
     * @Description: 第五步：处理登录失败的情况
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result result = Result.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            String json = JSONObject.toJSONString(result);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }
        return false;
    }

    /**
     * 获取请求的token
     * PS：由于在header里面我没取到token，所以才放到参数里面多加了一个尝试一下
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getHeader("Authorization");
        }

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }

        return token;
    }


}
