package effyroth.DanmakuPool.handler;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import spark.Route;

import com.google.common.collect.Maps;

import effyroth.DanmakuPool.common.JacksonUtils;
import effyroth.DanmakuPool.common.RequestUtils;
import effyroth.DanmakuPool.model.DanmakuPool;
import effyroth.DanmakuPool.service.DanmakuPoolService;
import effyroth.DanmakuPool.view.CodeMsg;
import effyroth.DanmakuPool.view.DanmakuPoolView;
import effyroth.DanmakuPool.view.ListPage;
import effyroth.DanmakuPool.view.QueryParameters;

@Slf4j
public class DanmakuPoolHandler {
	
	private DanmakuPoolService danmakuPoolService = new DanmakuPoolService();
	
	public Route get = (request, response) -> {

		int pageNo = RequestUtils.getIntParameter(request, "page_no", 1) ;
		int pageSize = RequestUtils.getIntParameter(request, "page_size", 10) ;
		
		QueryParameters queryParams = new QueryParameters();
		queryParams.setPageNo(pageNo);
		queryParams.setPageSize(pageSize);

		ListPage<DanmakuPool> danakus = danmakuPoolService.get(queryParams);
		ListPage<DanmakuPoolView> listPage = DanmakuPoolView.fromModelList(danakus);
		return CodeMsg.success().data(listPage);

	};
	public Route post = (request, response) -> {

		String dataJson = request.body();
		log.info("body = \n" + dataJson);
		
		DanmakuPool danaku = null;
		
		try {
			danaku = JacksonUtils.toBean(dataJson, DanmakuPool.class);
			log.info("danaku" + danaku);
			
			long danakuId = danmakuPoolService.save(danaku);
			
			Map<String, Object> data = Maps.newHashMap();
			data.put("id", String.valueOf(danakuId));
			return CodeMsg.success().data(data);
		} catch (Exception e1) {
			log.error(String.format("parse danaku json error %s", e1.getMessage()), e1);
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
