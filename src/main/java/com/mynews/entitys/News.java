package com.mynews.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity(name="news")
@EntityListeners(AuditingEntityListener.class)
public class News {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	
	@Column(length=10000)
	private String content;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
	@CreatedDate
	private Date createtime;
	
	private String url;
	
	private String imgurl;
	
	private String Month; //所属月份
	
	private String year; //所属年份
}
