package com.oracle.gdms.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.dao.GoodsDao;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.PageModel;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.GDMSUtil;
import com.oracle.gdms.web.listener.AppListener;

public class GoodsServiceImpl extends BaseService implements GoodsService {

	@Override
	public PageModel<GoodsModel> findByPage(int pageNumber, int rows) {
		PageModel<GoodsModel> obj = new PageModel<GoodsModel>();
		obj.setCurrent(pageNumber);
		try {
			session = GDMSUtil.getSesstion();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			// 查询总记录行数
			int count = dao.findCount();
			// 计算总页数
			int total = count % rows == 0 ? count / rows : count / rows + 1;
			obj.setTotal(total);
			// 计算起始位置
			int offset = (pageNumber - 1) * rows;
			Map<String, Integer> map = new HashMap<>();
			map.put("offset", offset);
			map.put("rows", rows);
			obj.setData(dao.findByPage(map));// 查数据集

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}

		return obj;
	}

	@Override
	public String pushGoods(int goodsid) {
		try {
			session = GDMSUtil.getSesstion();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			GoodsModel goods = dao.findById(goodsid);
			JSONObject json = new JSONObject();
			json.put("goods", goods);
			String jsonstr = json.toJSONString();
			
			ResponseEntity result = push(jsonstr);// 执行推送
			if (result != null && result.getCode() == 0) {
				dao.updatePush(goodsid);//设置推送状态为已推送
				session.commit();
				return result.getMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return "推送失败";
	}

	// private int updatePush(int goodsid, GoodsDao dao) {
	// int c = dao.updatePush(goodsid);
	// return c;
	// }

	private ResponseEntity push(String jsonstr) {
		// 定义一个要推送的地址
		String url = AppListener.getString("pushurl_self");

		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonstr, "UTF-8");// 构造参数实体
		entity.setContentType("application/json");
		post.setEntity(entity);// 为post请求设置请求参数
		HttpClient client = new DefaultHttpClient();
		try {
			// 用client对象执行post请求,并把响应放进HttpResponse对象中
			HttpResponse resp = client.execute(post);
			HttpEntity resent = resp.getEntity();

			String str = EntityUtils.toString(resent);
			ResponseEntity re = JSONObject.parseObject(str, ResponseEntity.class);
			return re;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int add(GoodsEntity goods) {
		try {
			session = GDMSUtil.getSesstion();
			GoodsDao dao = session.getMapper(GoodsDao.class);
			int c = dao.add(goods);
			session.commit();
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return 0;
	}

}
