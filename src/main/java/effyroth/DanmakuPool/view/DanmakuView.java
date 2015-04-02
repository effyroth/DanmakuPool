package effyroth.DanmakuPool.view;

import java.util.List;

import lombok.Data;

import com.google.common.collect.Lists;

import effyroth.DanmakuPool.model.Danmaku;
import effyroth.DanmakuPool.model.DanmakuPool;

@Data
public class DanmakuView {
	
	private String id;
	
	private String msg;
	
	private float playTime;
	
	private int color;
	
	private int fontSize;
	
	private int mode;

	public static DanmakuView fromModel(Danmaku danmaku){
		DanmakuView danmakuView = new DanmakuView();
		danmakuView.setId(String.valueOf(danmaku.getId()));
		danmakuView.setMsg(danmaku.getMsg());
		danmakuView.setPlayTime(danmaku.getPlayTime());
		danmakuView.setColor(danmaku.getColor());
		danmakuView.setFontSize(danmaku.getFontSize());
		danmakuView.setMode(danmaku.getMode());
		return danmakuView;
	}
	
	public static ListPage<DanmakuView> fromModelList(ListPage<Danmaku> listPage){
		List<DanmakuView> viewList = Lists.newArrayList();
		if(listPage.getDataList() != null){
			for(Danmaku danmaku : listPage.getDataList()){
				DanmakuView danakuView = fromModel(danmaku);
				viewList.add(danakuView);
			}
		}
		
		ListPage<DanmakuView> viewListPage = new ListPage<DanmakuView>();
		viewListPage.setCurrentPageNo(listPage.getCurrentPageNo());
		viewListPage.setCurrentPageSize(listPage.getCurrentPageSize());
		viewListPage.setTotalCount(listPage.getTotalCount());
		viewListPage.setDataList(viewList);
		return viewListPage;
	}
}
