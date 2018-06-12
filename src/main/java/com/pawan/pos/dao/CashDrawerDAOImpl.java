package com.pawan.pos.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pawan.pos.model.CashDrawer;

@Repository
@Transactional
public class CashDrawerDAOImpl implements CashDrawerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public CashDrawer saveCashDrawer(CashDrawer cashDrawer) {
		getCurrentSession().save(cashDrawer);
		Query query = getCurrentSession().createQuery("from CashDrawer where id=:ID").setParameter("ID",
				cashDrawer.getId());

		return (CashDrawer) query.uniqueResult();
	}

	@Override
	public void UpdateCashDrawer(CashDrawer cashDrawer) {
		getCurrentSession().update(cashDrawer);
	}

}
