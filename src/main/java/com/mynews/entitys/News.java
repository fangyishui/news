package com.mynews.entitys;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
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

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
	@CreatedDate
	private Date createTime;

	@Column(length=10000)
	private String url;

	private String imgUrl;

	private Integer newsType;

	@Column(length=10000)
	private String content;


	private Integer year; //所属年份


	private Integer month; //所属月份

}
