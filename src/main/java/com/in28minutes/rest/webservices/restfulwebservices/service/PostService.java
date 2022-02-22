package com.in28minutes.rest.webservices.restfulwebservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.in28minutes.rest.webservices.restfulwebservices.entity.Post;
import com.in28minutes.rest.webservices.restfulwebservices.repository.PostRepository;

@Service
public class PostService {

  private PostRepository repository;

  public Post save(Post novoPost) {
    return repository.save(novoPost);
  }

  public Optional<Post> findById(long id) {
    return repository.findById(id);
  }

  public List<Post> findAll() {
    return repository.findAll();
  }
}
