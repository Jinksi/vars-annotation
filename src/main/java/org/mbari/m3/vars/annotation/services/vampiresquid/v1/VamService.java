package org.mbari.m3.vars.annotation.services.vampiresquid.v1;

import org.mbari.m3.vars.annotation.gson.ByteArrayConverter;
import org.mbari.m3.vars.annotation.model.Media;
import org.mbari.m3.vars.annotation.services.AuthService;
import org.mbari.m3.vars.annotation.services.MediaService;
import org.mbari.m3.vars.annotation.services.RetrofitWebService;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author Brian Schlining
 * @since 2017-05-27T12:32:00
 */
public class VamService implements MediaService, RetrofitWebService {

    private final VamWebService vamWebService;
    private final Map<String, String> defaultHeaders;

    public VamService(VamWebServiceFactory serviceFactory, AuthService authService) {
        vamWebService = serviceFactory.create(VamWebService.class, authService);
        defaultHeaders = new HashMap<>();
        defaultHeaders.put("Accept", "application/json");
        defaultHeaders.put("Accept-Charset", "utf-8");
    }

    @Override
    public CompletableFuture<Media> create() {
        return null;
    }

    @Override
    public CompletableFuture<Media> findByUuid(UUID uuid) {
        return sendRequest(vamWebService.findByUuid(uuid));
    }

    @Override
    public CompletableFuture<Media> findBySha512(byte[] sha512) {
        String hex = ByteArrayConverter.encode(sha512);
        return sendRequest(vamWebService.findBySha512(hex));
    }

    @Override
    public CompletableFuture<List<Media>> findByVideoSequenceName(String videoSequenceName) {
        return sendRequest(vamWebService.findByVideoSequenceName(videoSequenceName));
    }

    @Override
    public CompletableFuture<List<Media>> findByVideoName(String videoName) {
        return sendRequest(vamWebService.findByVideoName(videoName));
    }

    @Override
    public CompletableFuture<List<String>> findAllVideoSequenceNames() {
        return sendRequest(vamWebService.findAllVideoSequenceNames());
    }

    @Override
    public CompletableFuture<List<Media>> findByCameraIdAndTimestamp(String cameraId, Instant timestamp) {
        return sendRequest(vamWebService.findByCameraIdAndTimestamp(cameraId, timestamp));
    }

    @Override
    public CompletableFuture<List<Media>> findByVideoSequenceNameAndTimestamp(String videoSequenceName, Instant timestamp) {
        return CompletableFuture.completedFuture(new ArrayList<>());
        // TODO implement this
    }

    @Override
    public CompletableFuture<List<String>> findAllCameraIds() {
        return sendRequest(vamWebService.findAllCameraIds());
    }
}