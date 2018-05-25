package com.xa.dt.springboot.dao;

import java.util.List;
import com.xa.dt.springboot.bean.Article;

public interface BaseDao {

	List<Article> query(Article article);
}
