package com.foobar.mysql.repo;

import com.foobar.mysql.domain.MyFoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFooRepository extends JpaRepository<MyFoo, Long> {
}
