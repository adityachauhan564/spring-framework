package com.jbdl63.digitallibrary.service;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.jbdl63.digitallibrary.dto.RangeDataDto;
import com.jbdl63.digitallibrary.model.Author;

/*
 * Redis used directly (not as a cache). Redis = a very fast store that keeps data in memory.
 * Here are its four basic data structures, one key each:
 *   String  AUTHOR::<id>        one value per key, here with a 30 s expiry
 *   List    AUTHOR::_List       in order, duplicates allowed (works as a queue or a stack)
 *   Set     AUTHOR::_Set        no order, no duplicates, can give back random members
 *   Hash    AUTHOR::_DataInHash a map inside one key: field -> value
 * Watch it happen:  docker exec -it digital-library-redis redis-cli   then  KEYS *   GET "AUTHOR::1"
 */
@Service
@Profile("redis")
public class RedisService {

    private static final String KEY = "AUTHOR::";

    private final RedisTemplate<String, Author> redisTemplate;

    public RedisService(RedisTemplate<String, Author> authorRedisTemplate) {
        this.redisTemplate = authorRedisTemplate;
    }

    public boolean addNewData(Author author) {
        Boolean stored = redisTemplate.opsForValue().setIfAbsent(KEY + author.getAuthorId(), author, Duration.ofSeconds(30));
        return Boolean.TRUE.equals(stored);      // false = the key was already there, so nothing was changed
    }

    public Author getAuthorDetailsUsingId(Integer id) {
        return redisTemplate.opsForValue().get(KEY + id);
    }

    public Author getAndDeleteById(Integer id) {
        return redisTemplate.opsForValue().getAndDelete(KEY + id);
    }

    public void addNewDataToList(Author author) {
        redisTemplate.opsForList().leftPush(KEY + "_List", author);   // leftPush = newest first
    }

    public RangeDataDto fetchAuthorsUsingRange(int start, int end) {
        List<Author> range = redisTemplate.opsForList().range(KEY + "_List", start, end);
        return new RangeDataDto(range.size(), range);
    }

    public void addNewDataToSet(Author author) {
        redisTemplate.opsForSet().add(KEY + "_Set", author);   // adding the same author twice still stores it only once
    }

    public List<Author> getRandomMembers(int count) {
        return redisTemplate.opsForSet().randomMembers(KEY + "_Set", count);
    }

    public void addNewDataToHash(Author author) {
        redisTemplate.<String, Author>opsForHash().put(KEY + "_DataInHash", String.valueOf(author.getAuthorId()), author);
    }

    public Map<String, Author> getHash() {
        return redisTemplate.<String, Author>opsForHash().entries(KEY + "_DataInHash");
    }
}
