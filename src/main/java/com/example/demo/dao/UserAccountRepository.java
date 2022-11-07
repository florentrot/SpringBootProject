package com.example.demo.dao;

import com.example.demo.entities.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "apiusers", path = "apiusers")
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

}
