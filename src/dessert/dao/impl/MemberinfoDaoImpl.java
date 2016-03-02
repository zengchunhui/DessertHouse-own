package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.MemberinfoDao;
import dessert.entity.Memberinfo;

@Repository
@Transactional
public class MemberinfoDaoImpl extends BaseDaoImpl<Memberinfo> implements MemberinfoDao{

	@Override
	public Memberinfo getById(String id) {
		return getByColumn(Memberinfo.class, "id", Integer.parseInt(id));
	}

}
