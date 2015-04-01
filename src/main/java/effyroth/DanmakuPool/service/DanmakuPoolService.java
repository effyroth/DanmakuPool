package effyroth.DanmakuPool.service;

import java.util.Date;

import effyroth.DanmakuPool.common.IdUtil;
import effyroth.DanmakuPool.dao.DanmakuPoolDao;
import effyroth.DanmakuPool.model.DanmakuPool;
import effyroth.DanmakuPool.view.ListPage;
import effyroth.DanmakuPool.view.QueryParameters;

public class DanmakuPoolService {
	private DanmakuPoolDao danmakuPoolDao = new DanmakuPoolDao();

	public DanmakuPool get() {
		return danmakuPoolDao.get();
	}

	public DanmakuPool get(long id) {
		return danmakuPoolDao.get(id);
	}

	public ListPage<DanmakuPool> get(QueryParameters queryParams) {
		return danmakuPoolDao.get(queryParams);
	}

	public long save(DanmakuPool survey) {
		survey.setId(IdUtil.nextId());
		survey.setCreatedAt(new Date().getTime());

		danmakuPoolDao.save(survey);
		return survey.getId();
	}
}
