package com.mynews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("news")
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	private String title;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//	@TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
	private Date createTime;

	private String url;

	private String imgUrl;

	private Integer newsType;

	private String content;

	private Integer year; //所属年份

	private Integer month; //所属月份

}
