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
import com.oracle.gdms.entity.GoodsModel;
import com.oracle.gdms.entity.PageModel;
import com.oracle.gdms.entity.ResponseEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.GDMSUtil;

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
			push(jsonstr);//执行推送
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free();
		}
		return "";
	}

	private void push(String jsonstr) {
		//定义一个要推送的地址
		String url = "http://172.19.133.26:8080/gdms-web/rest/goods/push";
		
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(jsonstr, "UTF-8");//构造参数实体
		entity.setContentType("application/json");
		post.setEntity(entity);//为post请求设置请求参数
		HttpClient client = new DefaultHttpClient();
		try {
			//用client对象执行post请求,并把响应放进HttpResponse对象中
			HttpResponse resp = client.execute(post);
			HttpEntity resent = resp.getEntity();
			
			String str = EntityUtils.toString(resent);
			ResponseEntity re = JSONObject.parseObject(str, ResponseEntity.class);
			System.out.println("code=  "+re.getCode()+"    msg=  "+re.getMessage());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
