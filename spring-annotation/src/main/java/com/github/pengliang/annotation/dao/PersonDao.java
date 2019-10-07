package com.github.pengliang.annotation.dao;

import com.github.pengliang.annotation.bean.Person;
import org.springframework.stereotype.Repository;

/**
 * @author pengliang  2019-03-14 15:54
 */
@Repository
public interface PersonDao {

    Person getPersionByName(String name);
}
