package dessert.dao;

import dessert.entity.Cardinfo;

public interface CardinfoDao extends BaseDao<Cardinfo>{
   public Cardinfo getById(String id);
}
