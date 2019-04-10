package org.launchcode.cheesemvs.models.data;

import org.launchcode.cheesemvs.models.Category;
import org.launchcode.cheesemvs.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
