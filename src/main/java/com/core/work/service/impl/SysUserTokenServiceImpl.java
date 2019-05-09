package com.core.work.service.impl;

import com.core.work.entity.SysUserEntity;
import com.core.work.entity.SysUserTokenEntity;
import com.core.work.entity.vo.SysUserVo;
import com.core.work.repository.SysUserRepository;
import com.core.work.repository.SysUserTokenRepository;
import com.core.work.service.SysUserTokenService;
import com.core.work.shiro.TokenGenerator;
import com.core.work.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {
	@Autowired
	private SysUserTokenRepository sysUserTokenRepository;
	@Autowired
	private SysUserRepository sysUserRepository;

	//12小时后过期
	private final static int EXPIRE = 3600 * 12;


	@Override
	public Result createToken(String userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = sysUserTokenRepository.findByUserId(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			sysUserTokenRepository.save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			sysUserTokenRepository.save(tokenEntity);
		}

		SysUserEntity sysUserEntity=sysUserRepository.findById(userId).orElse(null);
		Result result = Result.ok().put("token", token).put("expire", EXPIRE).put("user", SysUserVo.getHomeVoByEntity(sysUserEntity));


		return result;
	}

	@Override
	public void logout(String userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		sysUserTokenRepository.save(tokenEntity);
	}
}
