package effyroth.DanmakuPool.view;

import java.util.List;

import lombok.Data;

import com.google.common.collect.Lists;

import effyroth.DanmakuPool.model.DanmakuPool;

@Data
public class DanmakuPoolView {
	
	private String id;
	
	public static DanmakuPoolView fromModel(DanmakuPool danmakuPool){
		DanmakuPoolView danmakuPoolView = new DanmakuPoolView();
		danmakuPoolView.setId(String.valueOf(danmakuPool.getId()));
		return danmakuPoolView;
	}
	
	public static ListPage<DanmakuPoolView> fromModelList(ListPage<DanmakuPool> listPage){
		List<DanmakuPoolView> viewList = Lists.newArrayList();
		if(listPage.getDataList() != null){
			for(DanmakuPool danmakuPool : listPage.getDataList()){
				DanmakuPoolView danmakuPoolView = fromModel(danmakuPool);
				viewList.add(danmakuPoolView);
			}
		}
		
		ListPage<DanmakuPoolView> viewListPage = new ListPage<DanmakuPoolView>();
		viewListPage.setCurrentPageNo(listPage.getCurrentPageNo());
		viewListPage.setCurrentPageSize(listPage.getCurrentPageSize());
		viewListPage.setTotalCount(listPage.getTotalCount());
		viewListPage.setDataList(viewList);
		return viewListPage;
	}

}
