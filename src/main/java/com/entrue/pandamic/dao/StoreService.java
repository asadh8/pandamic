package com.entrue.pandamic.dao;

import com.entrue.pandamic.respository.StoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StoreService {

    @Autowired
    private StoresRepository storesRepository;

    @PostConstruct
    public void check() {
        if (storesRepository == null) {
            // something is wrong
        }
    }

    public StoresRepository getRepository() {
        return storesRepository;
    }

}
