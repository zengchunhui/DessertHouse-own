package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.CardinfoDao;
import dessert.entity.Cardinfo;

@Repository
@Transactional
public class CardinfoDaoImpl extends BaseDaoImpl<Cardinfo> implements CardinfoDao{

	@Override
	public Cardinfo getById(String id) {
		
		return getByColumn(Cardinfo.class, "id", Integer.parseInt(id));
	}

}
