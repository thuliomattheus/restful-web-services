package com.in28minutes.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in28minutes.rest.webservices.restfulwebservices.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
