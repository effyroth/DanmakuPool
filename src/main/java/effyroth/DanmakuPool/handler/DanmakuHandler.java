package effyroth.DanmakuPool.handler;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import spark.Route;

import com.google.common.collect.Maps;

import effyroth.DanmakuPool.common.JacksonUtils;
import effyroth.DanmakuPool.common.RequestUtils;
import effyroth.DanmakuPool.model.Danmaku;
import effyroth.DanmakuPool.service.DanmakuService;
import effyroth.DanmakuPool.view.CodeMsg;
import effyroth.DanmakuPool.view.DanmakuView;
import effyroth.DanmakuPool.view.ListPage;
import effyroth.DanmakuPool.view.QueryParameters;

@Slf4j
public class DanmakuHandler {
	
	private DanmakuService danmakuService = new DanmakuService();
	
	public Route get = (request, response) -> {

		long poolId = Long.parseLong(request.params(":danmakupool_id").trim());
		
		int pageNo = RequestUtils.getIntParameter(request, "page_no", 1) ;
		int pageSize = RequestUtils.getIntParameter(request, "page_size", 10) ;
		
		QueryParameters queryParams = new QueryParameters();
		queryParams.setPageNo(pageNo);
		queryParams.setPageSize(pageSize);

		ListPage<Danmaku> danmakus = danmakuService.get(poolId, queryParams);
		ListPage<DanmakuView> listPage = DanmakuView.fromModelList(danmakus);
		return CodeMsg.success().data(listPage);


	};
	public Route post = (request, response) -> {

		String dataJson = request.body();
		log.info("body = \n" + dataJson);
		
		long poolId = Long.parseLong(request.params(":danmakupool_id").trim());
		Danmaku danmaku = null;
		
		try {
			danmaku = JacksonUtils.toBean(dataJson, Danmaku.class);
			danmaku.setPoolId(poolId);
			log.info("danmaku" + danmaku);
			
			long danmakuId = danmakuService.save(danmaku);
			
			Map<String, Object> data = Maps.newHashMap();
			data.put("id", String.valueOf(danmakuId));
			return CodeMsg.success().data(data);
		} catch (Exception e1) {
			log.error(String.format("parse danmaku json error %s", e1.getMessage()), e1);
			return CodeMsg.failure(400, "数据格式错误," + e1.getMessage());
		}

	};
	public Route put = (request, response) -> {

		return request;

	};
	public Route delete = (request, response) -> {

		return request;

	};
}
