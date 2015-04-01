package effyroth.DanmakuPool.server;

import spark.ResponseTransformer;
import effyroth.DanmakuPool.common.JacksonUtils;

public class JacksonTransformer implements ResponseTransformer {

	@Override
	public String render(Object model) throws Exception {
		return JacksonUtils.toJson(model);
	}

}
