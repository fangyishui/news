package com.mynews.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mynews.entity.News;

import java.util.List;

public interface  NewsMapper extends BaseMapper<News> {

    boolean InsertBatchSomeColumn(List<News> entityList, int batchSize);
}
