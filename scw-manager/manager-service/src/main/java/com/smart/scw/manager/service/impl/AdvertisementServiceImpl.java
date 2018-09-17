package com.smart.scw.manager.service.impl;

import com.smart.scw.manager.bean.TAdvertisement;
import com.smart.scw.manager.dao.TAdvertisementMapper;
import com.smart.scw.manager.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private TAdvertisementMapper tAdvertisementMapper;

    @Override
    public boolean addAdver(TAdvertisement advertisement) {
        return tAdvertisementMapper.insert(advertisement)>0;
    }

    @Override
    public List<TAdvertisement> getAll() {
        return tAdvertisementMapper.selectAll();
    }


}
