package com.mynews.entitys;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

//@Entity(name="dic")
//@Data
//@EntityListeners(AuditingEntityListener.class)
//public class Dic {
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Integer id;
//	
//	//新闻来源
//	private String newsType;
//	
//	private String  desc;
//}
