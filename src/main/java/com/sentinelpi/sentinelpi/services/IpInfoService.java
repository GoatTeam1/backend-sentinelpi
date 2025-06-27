package com.sentinelpi.sentinelpi.services;

import com.sentinelpi.sentinelpi.dto.IpInfoDto;
import com.sentinelpi.sentinelpi.models.IpInfo;
import com.sentinelpi.sentinelpi.repositories.IpInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IpInfoService {

    @Autowired
    private IpInfoRepository ipInfoRepository;

    public List<IpInfo> findAll() {
        return ipInfoRepository.findAll();
    }

    public Optional<IpInfo> findById(String id) {
        return ipInfoRepository.findById(id);
    }

    public IpInfo create(IpInfoDto dto) {

        IpInfo ipInfo = new IpInfo();
        ipInfo.setIp(dto.getIp());
        ipInfo.setLastActivity(dto.getLastActivity());
        ipInfo.setGeolocation(dto.getGeolocation());

        return ipInfoRepository.save(ipInfo);
    }

    public Optional<IpInfo> update(String id, IpInfoDto dto) {
        return ipInfoRepository.findById(id).map(existing -> {
            existing.setIp(dto.getIp());
            existing.setLastActivity(dto.getLastActivity());
            existing.setGeolocation(dto.getGeolocation());
            return ipInfoRepository.save(existing);
        });
    }

    public boolean delete(String id) {
        if (ipInfoRepository.existsById(id)) {
            ipInfoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
