package com.core.modules.sys.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统日志
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_log")
@Where(clause = "is_delete=0")
public class SysLogEntity extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "userName", value = "用户名", dataType = "String")
	@Column(name = "user_name", length = 100)
	private String userName;

	@ApiModelProperty(name = "userId", value = "用户ID", dataType = "String")
	@Column(name = "user_id", length = 50)
	private String userId;

	@ApiModelProperty(name = "operation", value = "用户操作", dataType = "String")
	@Column(name = "operation", length = 100)
	private String operation;

	@ApiModelProperty(name = "method", value = "请求方法", dataType = "String")
	@Column(name = "method", length = 200)
	private String method;

	@ApiModelProperty(name = "params", value = "请求参数", dataType = "String")
	@Column(name = "params", length = 255)
	private String params;

	@ApiModelProperty(name = "time", value = "执行时长(毫秒)", dataType = "Long")
	@Column(name = "time")
	private Long time;

	@ApiModelProperty(name = "ip", value = "IP地址", dataType = "String")
	@Column(name = "ip", length = 20)
	private String ip;
}
