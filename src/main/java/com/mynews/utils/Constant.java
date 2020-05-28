/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.mynews.utils;


/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";
    /**
     * 默认排序字段
     */
    public static final String SORT_CODE = "SORT_CODE";
    //文件上传相关
    /** 系统信息api */
    public static final String STATUS = "/status";
    /** 统计信息api */
    public static final String STAT = "/stat";
    /** 上传文件api */
    public static final String UPLOAD = "/upload";
    /** 删除文件api */
    public static final String DELETE = "/delete";
    /** 修复统计信息api */
    public static final String REPAIR_STAT = "/repair_stat";
    /** 删除空目录api */
    public static final String REMOVE_EMPTY_DIR = "/remove_empty_dir";
    /** 备份元数据api */
    public static final String BACKUP = "/backup";
    /** 同步失败修复api */
    public static final String REPAIR = "/repair";
    /** 文件列表api */
    public static final String LIST_DIR = "/list_dir";
    /** 文件信息api */
    public static final String GET_FILE_INFO = "/get_file_info";

	/**
	 * 菜单类型
	 *
	*
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
    *
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
