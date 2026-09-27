package com.jbdl63.digitallibrary.controller;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbdl63.digitallibrary.dto.RangeDataDto;
import com.jbdl63.digitallibrary.model.Author;
import com.jbdl63.digitallibrary.service.RedisService;

// A playground for the Redis data structures; exists only with the "redis" profile. See RedisService.
@RestController
@Profile("redis")
@RequestMapping(value = "/v1/redis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    // String
    @PostMapping
    public boolean addNewAuthor(@RequestBody Author author) {
        return redisService.addNewData(author);
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Integer id) {
        return redisService.getAuthorDetailsUsingId(id);
    }

    @DeleteMapping("/{id}")
    public Author getAndDelete(@PathVariable Integer id) {
        return redisService.getAndDeleteById(id);
    }

    // List
    @PostMapping("/list")
    public void addToList(@RequestBody Author author) {
        redisService.addNewDataToList(author);
    }

    @GetMapping("/list/{start}/{end}")
    public RangeDataDto getListRange(@PathVariable int start, @PathVariable int end) {
        return redisService.fetchAuthorsUsingRange(start, end);
    }

    // Set
    @PostMapping("/set")
    public void addToSet(@RequestBody Author author) {
        redisService.addNewDataToSet(author);
    }

    @GetMapping("/set/{count}")
    public List<Author> randomFromSet(@PathVariable int count) {
        return redisService.getRandomMembers(count);
    }

    // Hash
    @PostMapping("/hash")
    public void addToHash(@RequestBody Author author) {
        redisService.addNewDataToHash(author);
    }

    @GetMapping("/hash")
    public Map<String, Author> getHash() {
        return redisService.getHash();
    }
}
