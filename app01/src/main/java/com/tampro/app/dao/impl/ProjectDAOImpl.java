package com.tampro.app.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.app.dao.ProjectDAO;
import com.tampro.app.entity.Project;
import com.tampro.app.utils.Paging;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProjectDAOImpl  implements ProjectDAO{
	@Autowired
	EntityManager entity;
	//https://vladmihalcea.com/the-jpa-entitymanager-createnativequery-is-a-magic-wand/
	@Override
	public List<Project> findAllProjectByAccount(Map<String, Object> mapParams,long accountId,StringBuilder builder, Paging paging) {
		
		 // TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder(" select p.id, p.name,p.actual_end_date,p.actual_start_date , ");
		
		stringBuilder.append(" p.create_date,p.update_date , p.planned_end_date,p.planned_start_date ");
		stringBuilder.append(" ,p.project_description, p.stage , ua.username ");
		stringBuilder.append("  from project_manager pm ");
		stringBuilder.append(" inner join project p on pm.project_id = p.id");
		stringBuilder.append(" inner join user_account ua on ua.id = pm.account_id");
		stringBuilder.append(" where pm.account_id =  :id ");
		 
		 
		  StringBuilder stringBuilderCount = new StringBuilder(" select count(*) ");
		  stringBuilderCount.append("  from project_manager pm ");
		  stringBuilderCount.append(" inner join project p on pm.project_id = p.id	");
		  stringBuilderCount.append(" inner join user_account ua on ua.id = pm.account_id	");
		  stringBuilderCount.append(" where pm.account_id = :id ");
		
		if(builder != null ) {
			stringBuilder.append(builder);
			stringBuilderCount.append(builder);
		}
		 
		 Query query = entity.createNativeQuery(stringBuilder.toString(),Project.class);
		 Query count= entity.createNativeQuery(stringBuilderCount.toString());
		
		 query.setParameter("id", accountId);
		 count.setParameter("id", accountId);
		 if(mapParams != null) {
			for(String key : mapParams.keySet()) {				 
				query.setParameter(key, mapParams.get(key));
			 	count.setParameter(key, mapParams.get(key));
			}
		}
		
		  if(paging != null) {
			long total = ((Number) count.getSingleResult()).longValue();
			 
			paging.setTotalProduct((Long) total ); 
			query.setFirstResult(paging.getOffSet());
			query.setMaxResults(paging.getNumberPerPage());
		} 
		 
		
		return query.getResultList();
	}

}
