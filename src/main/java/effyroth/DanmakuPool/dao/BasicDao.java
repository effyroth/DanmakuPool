package effyroth.DanmakuPool.dao;

import java.util.List;

import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import effyroth.DanmakuPool.view.ListPage;
import effyroth.DanmakuPool.view.QueryParameters;

public class BasicDao<T, K> extends BasicDAO<T, K> {

	public BasicDao(Class<T> entityClass) {
		super(entityClass, DaoHelper.getDatastore());
	}

	public ListPage<T> queryListPage(Query<T> query, QueryParameters queryParams) {
		
    	if (query == null || queryParams == null) {
    		throw new IllegalArgumentException();
    	}
		
		
        long totalCount = query.countAll();
        List<T> data = query.offset(queryParams.getOffset()).limit(queryParams.getPageSize()).asList();
        
    	ListPage<T> listPage = new ListPage<T>();
      	listPage.setTotalCount(totalCount);
      	listPage.setDataList(data);
        listPage.setCurrentPageNo(queryParams.getPageNo());
      	listPage.setCurrentPageSize(queryParams.getPageSize());
        return listPage;
    }
}
