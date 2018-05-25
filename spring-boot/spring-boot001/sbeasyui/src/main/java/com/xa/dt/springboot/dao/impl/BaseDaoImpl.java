package com.xa.dt.springboot.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.xa.dt.springboot.bean.Article;
import com.xa.dt.springboot.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao {
	
	Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Article> query(Article article) {
		String sql = "select id, title, summary, status, type from article where 1 = 1 " + buildConditons(article);
		return jdbcTemplate.query(sql, new RowMapper<Article>() {

			@Override
			public Article mapRow(ResultSet arg0, int arg1) throws SQLException {
				log.info("RowMapper中的arg1[{}]", arg1);
				Article a = new Article();
				a.setId(arg0.getInt(1));
				a.setTitle(arg0.getString(2));
				a.setSummary(arg0.getString(3));
				a.setStatus(arg0.getInt(4));
				a.setType(arg0.getInt(5));
				return a;
			}

		});
	}

	private String buildConditons(Article article) {
		if (StringUtils.isEmpty(article)) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			if (article.getId() > 0) {
				sb.append(" and id = " + article.getId());
			}

			if (!StringUtils.isEmpty(article.getTitle())) {
				sb.append(" and title like '%" + article.getId() + "%'");
			}

			if (!StringUtils.isEmpty(article.getSummary())) {
				sb.append(" and summary like '%" + article.getSummary() + "%'");
			}

			if (article.getStatus() > 0) {
				sb.append(" and status = " + article.getStatus());
			}

			if (article.getType() > 0) {
				sb.append(" and type = " + article.getType());
			}

			return sb.toString();
		}
	}

}
