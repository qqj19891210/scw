package com.smart.scw.manager.service;

import com.smart.scw.manager.bean.TAdvertisement;

import java.util.List;

public interface AdvertisementService {

    boolean addAdver(TAdvertisement advertisement);

    List<TAdvertisement> getAll();
}
