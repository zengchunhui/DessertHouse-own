package dessert.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.configure.Configure;
import dessert.dao.AccountDao;
import dessert.dao.CardinfoDao;
import dessert.dao.InventoryDao;
import dessert.dao.MemberrecordDao;
import dessert.dao.ReservationDao;
import dessert.dao.StoreDao;
import dessert.entity.Account;
import dessert.entity.Cardinfo;
import dessert.entity.Inventory;
import dessert.entity.Memberrecord;
import dessert.entity.Reservation;
import dessert.entity.Store;
import dessert.pvo.CartItemPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.commodity.InventoryRVO;
import dessert.service.CommodityService;
import dessert.util.Util;

@Service
public class CommodityServiceImpl implements CommodityService{

	@Autowired
	StoreDao storeDao;
	@Autowired
	InventoryDao inventoryDao;
	@Autowired
	CardinfoDao cardinfoDao;
	@Autowired
	ReservationDao reservationDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	MemberrecordDao memberRecordDao;
	
	@Override
	public List<InventoryRVO> getByNameandDate(String name, Date date) {
		// TODO Auto-generated method stub
		List<InventoryRVO> resultList=new ArrayList<>();
		Store store=storeDao.getByName(name);
		if (store==null) {
//			System.out.println("name error");
			return resultList;
		}
//		System.out.println("store name"+store.getName());
		List<Inventory> list=inventoryDao.getByStoreIDandDate(store.getId(), date);
		for (int i = 0; i < list.size(); i++) {
			InventoryRVO rvo=new InventoryRVO();
			rvo.setFromInventory(list.get(i));
			resultList.add(rvo);
		}
		return resultList;
	}

	@Override
	public ResultVO reservate(List<CartItemPVO> pvos, String id) {
		// TODO Auto-generated method stub
		Map<Integer,Double> account=new HashMap<>();
		Timestamp currenttime=Util.getCurrentTimeStamp();
		Cardinfo cardinfo=cardinfoDao.getById(id);
		ResultVO rVo=new ResultVO();
		double amount=0.0;
		if (cardinfo==null) {
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您尚未注册，不能预定");
			return rVo;
		}
		for (int i = 0; i < pvos.size(); i++) {
			amount+=pvos.get(i).getPrice()*pvos.get(i).getP_num();
		}
		if (amount>cardinfo.getBalance()) {//检查余额
			rVo.setSuccess(Configure.FAIL);
			rVo.setMessage("您的余额不足");
			return rVo;
		}
		
		for (int i = 0; i < pvos.size(); i++) {
			CartItemPVO pvo=pvos.get(i);
			double money=pvo.getPrice()*pvo.getP_num();
			Store store=storeDao.getByName(pvo.getStore_name());
			Reservation reservation=new Reservation(id, pvo, currenttime, store.getId());
			if (account.containsKey(store.getId())) {
				account.replace(store.getId(), account.get(store.getId())+money);//记录账户变动
			}else {
				account.put(store.getId(), money);
			}
			reservationDao.add(reservation);/* ~  */
			Inventory inventory=inventoryDao.getByStoreIDandDateandName(store.getId(), pvo.getS_date(), pvo.getP_name());
			inventory.sale(pvo.getP_num());
			inventoryDao.update(inventory);/* ~ */
		}
		Memberrecord record=new Memberrecord();
		record.setM_id(Integer.parseInt(id));
		record.setR_date(currenttime);
		record.setAmount(amount);
		record.setType(Configure.RESERVE);
		record.setExplanation("在线预订");
		memberRecordDao.add(record);/* ~ */
		cardinfo.useBalance(amount);
		cardinfo.addIntegral(Util.getintegral(amount, cardinfo.getGrade()));
		cardinfoDao.update(cardinfo);/* ~ */
		for ( Map.Entry<Integer,Double> entry: account.entrySet()) {
			Account store_account=accountDao.getById(Account.class, entry.getKey());
			store_account.addAccount(entry.getValue());
			accountDao.update(store_account);/* ~ */
		}
		rVo.setSuccess(Configure.SUCCESS_INT);
		rVo.setMessage("预定成功");
		return rVo;
	}

}
