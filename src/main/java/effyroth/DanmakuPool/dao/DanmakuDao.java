package effyroth.DanmakuPool.dao;

import org.mongodb.morphia.query.Query;

import effyroth.DanmakuPool.model.Danmaku;
import effyroth.DanmakuPool.model.DanmakuPool;
import effyroth.DanmakuPool.view.ListPage;
import effyroth.DanmakuPool.view.QueryParameters;

public class DanmakuDao extends BasicDao<Danmaku, Long>{

	public DanmakuDao() {
		super(Danmaku.class);
		// TODO Auto-generated constructor stub
	}
	
	public ListPage<Danmaku> get(long poolId, QueryParameters queryParams) {
		Query<Danmaku> query = this.createQuery().filter("pool_id", poolId)
				.order("-_id");
		return queryListPage(query, queryParams);
	}
}
