package com.sentinelpi.sentinelpi.controllers;

import com.sentinelpi.sentinelpi.dto.IpInfoDto;
import com.sentinelpi.sentinelpi.models.IpInfo;
import com.sentinelpi.sentinelpi.services.IpInfoService;
import com.sentinelpi.sentinelpi.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ip-info")
public class IpInfoController {

    @Autowired
    private IpInfoService ipInfoService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        if (size > 1000) size = 1000;

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "lastActivity");
        Page<IpInfo> infos = ipInfoService.findAll(pageable);

        if (infos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("No hay información de IP registrada", false, HttpStatus.NOT_FOUND));
        }

        return ResponseEntity.ok(new ApiResponse<>(infos.getContent(), true, HttpStatus.OK));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable String id) {
        return ipInfoService.findById(id)
                .<ResponseEntity<ApiResponse<?>>>map(ipInfo ->
                        ResponseEntity.ok(new ApiResponse<>(ipInfo, true, HttpStatus.OK)))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>("IP no encontrada", false, HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@Valid @RequestBody IpInfoDto dto) {
        try {
            IpInfo created = ipInfoService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(created, true, HttpStatus.CREATED));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable String id, @Valid @RequestBody IpInfoDto dto) {
        return ipInfoService.update(id, dto)
                .<ResponseEntity<ApiResponse<?>>>map(updated ->
                        ResponseEntity.ok(new ApiResponse<>(updated, true, HttpStatus.OK)))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>("IP no encontrada", false, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String id) {
        boolean deleted = ipInfoService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>("IP eliminada", true, HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("IP no encontrada", false, HttpStatus.NOT_FOUND));
        }
    }
}
