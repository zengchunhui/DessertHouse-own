package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.MemberrecordDao;
import dessert.entity.Memberrecord;

@Repository
@Transactional
public class MemberrecordDaoImpl extends BaseDaoImpl<Memberrecord> implements MemberrecordDao{

}
