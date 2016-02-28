package dessert.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dessert.configure.Configure;
import dessert.dao.InventoryDao;
import dessert.dao.PlanDao;
import dessert.dao.StoreDao;
import dessert.entity.Inventory;
import dessert.entity.Plan;
import dessert.entity.Store;
import dessert.pvo.PlanPVO;
import dessert.rvo.ResultVO;
import dessert.rvo.plan.PlanInfoResultVO;
import dessert.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanDao planDao;
	@Autowired
	StoreDao storeDao;
	@Autowired
	InventoryDao inventoryDao;
	
	@Override
	public ResultVO addPlans(ArrayList<PlanPVO> pvo) {
		ResultVO resultVO = new ResultVO();
		try {
			for (int i = 0; i < pvo.size(); i++) {
				PlanPVO vo = pvo.get(i);
				Store store = storeDao.getById(Store.class, Integer.toUnsignedLong(vo.getS_id()));
				if (store == null) {
					resultVO.setSuccess(Configure.FAIL);
					resultVO.setMessage("该店面不存在，请重新输入");
					return resultVO;
				}
				Plan plan = new Plan();
				plan.setP_name(vo.getP_name());
				plan.setP_num(vo.getP_num());
				plan.setPlandate(vo.getPlandate());
				plan.setPrice(vo.getPrice());
				plan.setS_id(vo.getS_id());
				planDao.add(plan);
			}

		} catch (Exception e) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("未知错误");
			return resultVO;
		}

		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("计划列表添加成功");
		return resultVO;
	}

	@Override
	public ResultVO updatePlan(PlanPVO pvo, String id) {
		ResultVO resultVO = new ResultVO();
		Plan plan=planDao.getById(Plan.class, Long.parseLong(id));
		if (plan==null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
			return resultVO;
		}
		
		if (plan.getState()==Configure.PASS) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该计划已通过审批，不可修改");
			return resultVO;
		}
			plan.setP_name(pvo.getP_name());
			plan.setP_num(pvo.getP_num());
			plan.setPlandate(pvo.getPlandate());
			plan.setPrice(pvo.getPrice());
			plan.setS_id(pvo.getS_id());
			planDao.update(plan);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("修改成功");
		
		return resultVO;
	}

	@Override
	public ResultVO deletePlan(String id) {
		ResultVO resultVO = new ResultVO();
		Plan plan=planDao.getById(Plan.class, Long.parseLong(id));
		if (plan==null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
			return resultVO;
		}
		if (plan.getState()==Configure.PASS) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("该计划已通过审批，不可修改");
			return resultVO;
		}
			planDao.delete(plan);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("修改成功");
		
		return resultVO;
	}

	@Override
	public ResultVO passPlan(String id) {
		ResultVO resultVO = new ResultVO();
		Plan plan=planDao.getById(Plan.class, Long.parseLong(id));
		if (plan==null) {
			resultVO.setSuccess(Configure.FAIL);
			resultVO.setMessage("找不到该计划");
		}else {	
			plan.setState(Configure.PASS);
			planDao.update(plan);
			//计划通过后，新增店面库存
			Inventory inventory=new Inventory();
			inventory.setFromPlan(plan);
			inventoryDao.add(inventory);
			resultVO.setSuccess(Configure.SUCCESS_INT);
			resultVO.setMessage("修改成功");
		}
		return resultVO;
	}

	@Override
	public ResultVO passPlanList(ArrayList<String> ids) {		
		ResultVO resultVO = new ResultVO();
		for (int i = 0; i < ids.size(); i++) {
			Plan plan=planDao.getById(Plan.class, Long.parseLong(ids.get(i)));
			if (plan==null) {
				continue;//忽略错误计划id
			}else {	
				plan.setState(Configure.PASS);
				planDao.update(plan);
			}
		}
		resultVO.setSuccess(Configure.SUCCESS_INT);
		resultVO.setMessage("修改成功");
		return resultVO;
	}

	@Override
	public List<PlanInfoResultVO> getAllPlan(int page) {
		if (page<=0) {
			page=1;
		}
		List<Plan> list=planDao.getAllByPage(Plan.class, page, 10);
		List<PlanInfoResultVO> resultVOs=new ArrayList<>();
		if (list==null) {
			return resultVOs;
		}
		for (int i = 0; i < list.size(); i++) {
			resultVOs.add(new PlanInfoResultVO(list.get(i)));
		}
		return resultVOs;
	}

	@Override
	public List<PlanInfoResultVO> getInpassPlan(int page) {
		if (page<=0) {
			page=1;
		}
		List<Plan> list=planDao.getListByColumn(Plan.class, "state", Configure.IMPASS, page, 10);
		List<PlanInfoResultVO> resultVOs=new ArrayList<>();
		if (list==null) {
			return resultVOs;
		}
		for (int i = 0; i < list.size(); i++) {
			resultVOs.add(new PlanInfoResultVO(list.get(i)));
		}
		return resultVOs;
	}

	@Override
	public List<PlanInfoResultVO> getPassPlan(int page) {
		if (page<=0) {
			page=1;
		}
		List<Plan> list=planDao.getListByColumn(Plan.class, "state", Configure.PASS, page, 10);
		List<PlanInfoResultVO> resultVOs=new ArrayList<>();
		if (list==null) {
			return resultVOs;
		}
		for (int i = 0; i < list.size(); i++) {
			resultVOs.add(new PlanInfoResultVO(list.get(i)));
		}
		return resultVOs;
	}

}
