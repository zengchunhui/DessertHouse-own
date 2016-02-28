package dessert.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dessert.dao.AccountDao;
import dessert.entity.Account;

@Repository
@Transactional
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao{

}
