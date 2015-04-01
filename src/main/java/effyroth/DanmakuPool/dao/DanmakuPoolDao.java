package effyroth.DanmakuPool.dao;

import org.mongodb.morphia.query.Query;

import effyroth.DanmakuPool.model.DanmakuPool;
import effyroth.DanmakuPool.view.ListPage;
import effyroth.DanmakuPool.view.QueryParameters;

public class DanmakuPoolDao extends BasicDao<DanmakuPool, Long>{
	
	public DanmakuPoolDao() {
		super(DanmakuPool.class);
		// TODO Auto-generated constructor stub
	}

	public DanmakuPool get() {
		Query<DanmakuPool> query = this.createQuery();
		return query.get();
	}
	
	public DanmakuPool get(long id) {
		Query<DanmakuPool> query = this.createQuery().filter("_id", id);
		return query.get();
	}
	
	public ListPage<DanmakuPool> get(QueryParameters queryParams) {
		Query<DanmakuPool> query = this.createQuery()
				.order("-_id");
		return queryListPage(query, queryParams);
	}

}
