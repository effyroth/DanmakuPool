package effyroth.DanmakuPool.service;

import java.util.Date;

import effyroth.DanmakuPool.common.IdUtil;
import effyroth.DanmakuPool.dao.DanmakuDao;
import effyroth.DanmakuPool.model.Danmaku;
import effyroth.DanmakuPool.model.DanmakuPool;
import effyroth.DanmakuPool.view.ListPage;
import effyroth.DanmakuPool.view.QueryParameters;

public class DanmakuService {
	private DanmakuDao danmakuDao = new DanmakuDao();
	
	public ListPage<Danmaku> get(long poolId, QueryParameters queryParams) {
		return danmakuDao.get(poolId, queryParams);
	}

	public long save(Danmaku danmaku) {
		danmaku.setId(IdUtil.nextId());
		danmaku.setCreatedAt(new Date().getTime());

		danmakuDao.save(danmaku);
		return danmaku.getId();
	}
}
