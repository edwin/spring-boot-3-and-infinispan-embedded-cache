package com.edw.controller;

import com.edw.bean.User;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.query.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *     com.edw.controller.IndexController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 28 Apr 2023 10:31
 */
@RestController
public class IndexController {

    @Autowired
    private EmbeddedCacheManager cacheManager;

    @GetMapping(path = "/")
    public HashMap index() {
        return new HashMap(){{
            put("hello", "world");
        }};
    }

    @GetMapping(path = "/add-user")
    public User addUsers(@RequestParam String name, @RequestParam Integer age, @RequestParam String address, @RequestParam String province) {
        cacheManager.getCache("user-cache").put(name, new User(name, age, address, province));
        return (User) cacheManager.getCache("user-cache").getOrDefault(name, new User());
    }

    @GetMapping(path = "/get-user")
    public User getUsers(@RequestParam String name) {
        return (User) cacheManager.getCache("user-cache").getOrDefault(name, new User());
    }


    @GetMapping(path = "/find-address")
    public List<User> findAddress(@RequestParam String address) {
        QueryFactory qf = Search.getQueryFactory(cacheManager.getCache("user-cache"));
        Query<User> q = qf.create("from com.edw.bean.User u where address = :address order by province desc");
        q.setParameter("address", address);

        return q.execute().list();
    }
}
