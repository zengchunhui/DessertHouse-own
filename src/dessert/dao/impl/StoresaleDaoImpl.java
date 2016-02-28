package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.StoresaleDao;
import dessert.entity.Storesale;

@Repository
@Transactional
public class StoresaleDaoImpl extends BaseDaoImpl<Storesale> implements StoresaleDao{

}
