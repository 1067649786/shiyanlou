/*package com.shiyanlou.tx.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.shiyanlou.tx.dao.AccountDao;
import com.shiyanlou.tx.service.AccountService;

public class AccountServiceImpl implements AccountService{

    private AccountDao accountDao;
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    @Override
    public void transfer(final String outer,final String inner,final int money) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                accountDao.out(outer, money);
                int i = 1/0;
                accountDao.in(inner, money);
            }
        });
    }

}*/

package com.shiyanlou.tx.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shiyanlou.tx.dao.AccountDao;
import com.shiyanlou.tx.service.AccountService;

@Transactional(propagation=Propagation.REQUIRED , isolation = Isolation.DEFAULT)
@Service("accountService")
public class AccountServiceImpl implements AccountService{
    @Resource(name="accountDao")
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    @Override
    public void transfer(String outer, String inner, int money) {
        accountDao.out(outer, money);
        int i = 1/0;
        accountDao.in(inner, money);
    }

}