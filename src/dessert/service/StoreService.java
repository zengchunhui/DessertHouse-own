package dessert.service;

import java.util.List;

import dessert.pvo.StorePVO;
import dessert.rvo.ResultVO;
import dessert.rvo.store.StoreRVO;

public interface StoreService {

	public ResultVO addStore(StorePVO pvo);
	
	public ResultVO deleteStore(String id);
	
	public ResultVO updateStore(StorePVO pvo,String id);
	
	public String[] getAllStoreName();
	
	public List<StoreRVO> getAllStoreNotDelete();
	
}
