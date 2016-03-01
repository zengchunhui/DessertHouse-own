package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.PlanDao;
import dessert.entity.Plan;

@Repository
@Transactional
public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao{

	@Override
	public Plan getById(String id) {
		return getByColumn(Plan.class, "id", id);
	}

}
