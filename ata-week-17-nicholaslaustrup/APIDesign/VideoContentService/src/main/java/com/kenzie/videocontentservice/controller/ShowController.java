package com.kenzie.videocontentservice.controller;

import com.kenzie.videocontentservice.controller.model.GetEpisodeInfoResponse;
import com.kenzie.videocontentservice.controller.model.GetShowInfoResponse;
import com.kenzie.videocontentservice.controller.model.PutEpisodeInfoRequest;
import com.kenzie.videocontentservice.controller.model.PutShowInfoRequest;
import com.kenzie.videocontentservice.service.ContentService;
import com.kenzie.videocontentservice.service.model.EpisodeInfo;
import com.kenzie.videocontentservice.service.model.ParentalGuideline;
import com.kenzie.videocontentservice.service.model.ShowInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/show")
public class ShowController {

    private ContentService contentService;

    ShowController(ContentService contentService) {
        this.contentService = contentService;
    }

    // Create your endpoints here and add the necessary annotations
    @PostMapping
    public ResponseEntity addNewShow(@RequestBody PutShowInfoRequest putShowInfoRequest){
        ShowInfo showInfo = new ShowInfo();
        showInfo.setShowId(UUID.randomUUID().toString());
        showInfo.setTitle(putShowInfoRequest.getTitle());
        showInfo.setGenre(putShowInfoRequest.getGenre());
        showInfo.setParentalGuideline(ParentalGuideline.valueOf(putShowInfoRequest.getParentalGuideline()));
        showInfo.setEpisodeLength(putShowInfoRequest.getEpisodeLength());
        contentService.createShow(showInfo);
        return ResponseEntity.ok(getShowResponse(showInfo));
    }

    @GetMapping("/{showId}")
    public ResponseEntity<GetShowInfoResponse> getShow(@PathVariable("showId") String showId) {
        ShowInfo showInfo = contentService.getShow(showId);
        if (showInfo == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getShowResponse(showInfo));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetShowInfoResponse>> getAllShows(){
        List<ShowInfo> showList = contentService.getAllShows();

        List<GetShowInfoResponse> getShowList = new ArrayList<>();
        for (ShowInfo show:showList) {
            getShowList.add(getShowResponse(show));
        }
        return ResponseEntity.ok(getShowList);
    }

    @PostMapping("/{showId}/season/{seasonNumber}/episode")
    public ResponseEntity addNewEpisode(@PathVariable("showId") String showId,
                                        @PathVariable("seasonNumber") Integer seasonNumber,
                                        @RequestBody PutEpisodeInfoRequest putEpisodeInfoRequest){
        EpisodeInfo episodeInfo = new EpisodeInfo();
        episodeInfo.setShowId(showId);
        episodeInfo.setSeasonNumber(seasonNumber);
        episodeInfo.setEpisodeNumber(putEpisodeInfoRequest.getEpisodeNumber());
        episodeInfo.setTitle(putEpisodeInfoRequest.getTitle());
        episodeInfo.setAverageRating(putEpisodeInfoRequest.getAverageRating());
        episodeInfo.setAired(putEpisodeInfoRequest.getAired());
        episodeInfo.setDescription(putEpisodeInfoRequest.getDescription());
        episodeInfo.setDatabaseId(putEpisodeInfoRequest.getDatabaseId());
        contentService.addEpisode(episodeInfo);
        return ResponseEntity.ok(getEpisodeResponse(episodeInfo));
    }

    @GetMapping("{showId}/season/{seasonNumber}/episode/{episodeNumber}")
    public ResponseEntity<GetEpisodeInfoResponse> getEpisode(@PathVariable("showId") String showId,
                                                             @PathVariable("seasonNumber") Integer seasonNumber,
                                                             @PathVariable("episodeNumber") Integer episodeNumber){
        EpisodeInfo episodeInfo = contentService.getEpisode(showId, seasonNumber.toString(), episodeNumber.toString());

        return ResponseEntity.ok(getEpisodeResponse(episodeInfo));
    }

    @GetMapping("{showId}/season/{seasonNumber}/episode/all")
    public ResponseEntity<List<GetEpisodeInfoResponse>> getAllEpisodes(@PathVariable("showId") String showId,
                                                             @PathVariable("seasonNumber") Integer seasonNumber){
        List<EpisodeInfo> episodeList = contentService.getAllEpisodesForShow(showId, seasonNumber);
        List<GetEpisodeInfoResponse> responseList = new ArrayList<>();
        for (EpisodeInfo episode:episodeList) {
            responseList.add(getEpisodeResponse(episode));
        }

        return ResponseEntity.ok(responseList);
    }


                /**            HELPER METHODS              **/
    private GetEpisodeInfoResponse getEpisodeResponse(EpisodeInfo episodeInfo){
        GetEpisodeInfoResponse episodeResponse = new GetEpisodeInfoResponse();
        episodeResponse.setShowId(episodeInfo.getShowId());
        episodeResponse.setSeasonNumber(episodeInfo.getSeasonNumber());
        episodeResponse.setEpisodeNumber(episodeInfo.getEpisodeNumber());
        episodeResponse.setTitle(episodeInfo.getTitle());
        episodeResponse.setNumberOfRatings(episodeInfo.getNumberOfRatings());
        episodeResponse.setAverageRating(episodeInfo.getAverageRating());
        episodeResponse.setAired(episodeInfo.getAired());
        episodeResponse.setDescription(episodeInfo.getDescription());
        episodeResponse.setDatabaseId(episodeResponse.getDatabaseId());
        return episodeResponse;
    }

    private GetShowInfoResponse getShowResponse(ShowInfo showInfo){
        GetShowInfoResponse showResponse = new GetShowInfoResponse();
        showResponse.setShowId(showInfo.getShowId());
        showResponse.setTitle(showInfo.getTitle());
        showResponse.setParentalGuideline(showInfo.getParentalGuideline().toString());
        showResponse.setEpisodeLength(showInfo.getEpisodeLength());
        showResponse.setGenre(showInfo.getGenre());
        showResponse.setAverageRating(showInfo.getAverageRating());
        showResponse.setNumberOfRatings(showInfo.getNumberOfRatings());
        showResponse.setNumberOfSeasons(showInfo.getNumberOfSeasons());
        return showResponse;
    }
}
