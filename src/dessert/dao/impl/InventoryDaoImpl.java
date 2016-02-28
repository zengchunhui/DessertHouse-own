package dessert.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.InventoryDao;
import dessert.entity.Inventory;

@Repository
@Transactional
public class InventoryDaoImpl extends BaseDaoImpl<Inventory> implements InventoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> getByStoreIDandDate(int ID, Date date) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Inventory.class);
		criteria.add(Restrictions.eq("s_id", ID));
		criteria.add(Restrictions.eq("s_date", date));
		criteria.addOrder(Order.asc("p_num"));
		return criteria.list();
	}

}
