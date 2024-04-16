package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

    private final Map<Long, Post> dataBase = new HashMap<>();
   private AtomicLong idHolder = new AtomicLong(0);

    public List<Post> all() {
        return new ArrayList<>(dataBase.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(dataBase.get(id));
    }

    public Post save(Post post) {
      if(post.getId()==0){
        long id = idHolder.incrementAndGet();
        post.setId(id);
        this.dataBase.put(id, post);
      }else{
        Optional<Post> optionalPost = getById(post.getId());
        Post oldPost = optionalPost.orElseThrow(()-> new NoSuchElementException("id "+post.getId()+" not found"));
        oldPost.setContent(post.getContent());
      }
      return post;
    }

    public void removeById(long id) {
      dataBase.remove(id);
    }
}
