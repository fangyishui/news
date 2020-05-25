package com.mynews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
//@Entity(name="news")
//@EntityListeners(AuditingEntityListener.class)
public class News {

//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	private String title;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
	private Date createTime;

//	@Column(length=10000)
	private String url;

	private String imgUrl;

	private Integer newsType;

//	@Column(length=10000)
	private String content;

//	@DateTimeFormat(pattern = "yyyy") //入参
//	@CreatedDate
	private Integer year; //所属年份

//	@DateTimeFormat(pattern = "MM") //入参
//	@CreatedDate
	private Integer month; //所属月份

}
