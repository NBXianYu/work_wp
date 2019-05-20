package com.core.modules.sys.controller;

import com.core.modules.sys.entity.SysUserEntity;
import com.core.modules.sys.entity.form.SysLoginForm;
import com.core.modules.sys.entity.form.UserForm;
import com.core.modules.sys.service.SysUserService;
import com.core.modules.sys.service.SysUserTokenService;
import com.core.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description: 登录相关
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/13 0013 上午 11:07
 */
@RestController
@Api(value = "后台登陆")
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;


    /***
     * @Author: 吴鹏
     * @Description: 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
//        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
//        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

        /**
         * 登录
         */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "登陆")
    public Map<String, Object> login(@RequestBody SysLoginForm form) throws IOException {

        //用户信息
        SysUserEntity user = sysUserService.queryByName(form.getUserName());
        //账号不存在、密码错误
        if (user == null || !user.getPassWord().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return Result.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return Result.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        Result result = sysUserTokenService.createToken(user.getId());
        return result;
    }


    /**
     * 退出
     */
    @GetMapping("/logout")
    @ApiOperation(value = "登出", notes = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.ok();
    }

    /**
     * @Description: 注册
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [userForm]
     * @return Result
     * @date 2019/3/19 0019 下午 14:21
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/regist")
    public Result regist(@RequestBody UserForm userForm){
        sysUserService.addSysUser(UserForm.getUserByUserForm(null, userForm));
        return Result.ok().putResult("注册成功");
    }

}
