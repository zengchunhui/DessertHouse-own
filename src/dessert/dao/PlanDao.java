package dessert.dao;

import dessert.entity.Plan;

public interface PlanDao extends BaseDao<Plan>{
  public Plan getById(String id);
}
