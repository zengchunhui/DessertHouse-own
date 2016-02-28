package dessert.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.dao.InventoryDao;
import dessert.dao.StoreDao;
import dessert.entity.Inventory;
import dessert.entity.Store;
import dessert.rvo.commodity.InventoryRVO;
import dessert.service.CommodityService;

@Service
public class CommodityServiceImpl implements CommodityService{

	@Autowired
	StoreDao storeDao;
	@Autowired
	InventoryDao inventoryDao;
	
	@Override
	public List<InventoryRVO> getByNameandDate(String name, Date date) {
		// TODO Auto-generated method stub
		List<InventoryRVO> resultList=new ArrayList<>();
		Store store=storeDao.getByName(name);
		if (store==null) {
			return resultList;
		}
		List<Inventory> list=inventoryDao.getByStoreIDandDate(store.getId(), date);
		for (int i = 0; i < list.size(); i++) {
			InventoryRVO rvo=new InventoryRVO();
			rvo.setFromInventory(list.get(i));
			resultList.add(rvo);
		}
		return resultList;
	}

}
