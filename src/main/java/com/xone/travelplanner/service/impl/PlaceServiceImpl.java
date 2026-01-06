package com.xone.travelplanner.service.impl;

import com.xone.travelplanner.config.MinioProperties;
import com.xone.travelplanner.core.Error;
import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.core.UserRole;
import com.xone.travelplanner.dto.PlaceDto;
import com.xone.travelplanner.dto.PlaceImageDto;
import com.xone.travelplanner.model.Place;
import com.xone.travelplanner.model.PlaceImage;
import com.xone.travelplanner.model.User;
import com.xone.travelplanner.repository.PlaceImageRepository;
import com.xone.travelplanner.repository.PlaceRepository;
import com.xone.travelplanner.service.PlaceService;
import com.xone.travelplanner.service.StorageService;
import io.minio.MinioClient;
import org.hibernate.event.internal.EntityState;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProperties minioProperties;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PlaceImageRepository placeImageRepository;

    @Override
    @Transactional
    public Place addPlace(PlaceDto placeDto, User currentUser) throws TravelException {
        if (currentUser == null || currentUser.getRole() != UserRole.Admin) {
            throw new TravelException(Error.PLACE_CREATION_RESTRICTED);
        }

        if (placeRepository.existsByTitleAndCityAndCountryAndLatitudeAndLongitude(
                placeDto.getTitle(), placeDto.getCity(), placeDto.getCountry(), placeDto.getLatitude(), placeDto.getLongitude())) {
            throw new TravelException(Error.PLACE_ALREADY_EXISTS);
        }

        Place place = Place.builder()
                .title(placeDto.getTitle())
                .city(placeDto.getCity())
                .country(placeDto.getCountry())
                .latitude(placeDto.getLatitude())
                .longitude(placeDto.getLongitude())
                .shortDescription(placeDto.getShortDescription())
                .rating(0L)
                .reviewCount(0L)
                .isFeatured(false)
                .createdBy(currentUser)
                .build();

        place.setEntityState(EntityState.PERSISTENT);

        Place savedPlace = placeRepository.save(place);

        if (placeDto.getImages() != null) {
            for (PlaceImageDto placeImageDto : placeDto.getImages()) {
                String imageUrl = storageService.uploadPlaceImage(savedPlace.getId(), placeImageDto.getBase64Content(), placeImageDto.getFileName(), placeImageDto.getContentType());
                saveImagePlace(savedPlace, placeImageDto, imageUrl);
            }
        }

        return savedPlace;
    }

    private void saveImagePlace(Place savedPlace, PlaceImageDto placeImageDto, String imageUrl) {
        PlaceImage imagePlace = PlaceImage.builder()
                .place(savedPlace)
                .imageUrl(imageUrl)
                .altText(placeImageDto.getAltText())
                .position(placeImageDto.getPosition())
                .build();

        imagePlace.setEntityState(EntityState.PERSISTENT);
        placeImageRepository.save(imagePlace);
    }

    @Override
    @Transactional
    public Place addImage(UUID placeId, String altText, Integer position, MultipartFile file, User user) throws TravelException {
        if (user == null || user.getRole() != UserRole.Admin) {
            throw new TravelException(Error.PLACE_CREATION_RESTRICTED);
        }

        if (file == null || file.isEmpty()) {
            throw new TravelException(Error.IMAGE_UPLOAD_FAILED);
        }

        Place place = getPlaceById(placeId);

        String imageUrl = storageService.uploadPlaceImage(placeId, file);

        if (place.getImages() == null) {
            place.setImages(new ArrayList<>());
        }

        PlaceImage image = PlaceImage.builder()
                .place(place)
                .imageUrl(imageUrl)
                .altText(altText)
                .position(position != null ? position : place.getImages().size())
                .build();
        image.setEntityState(EntityState.PERSISTENT);

        place.getImages().add(image);
        return placeRepository.save(place);
    }

    private void preparePlaceImages(Place place, List<PlaceImage> images) throws TravelException {
        if (images == null) {
            place.setImages(new ArrayList<>());
            return;
        }

        place.setImages(new ArrayList<>());

        for (int index = 0; index < images.size(); index++) {
            PlaceImage image = images.get(index);
            if ((image.getImageUrl() == null || image.getImageUrl().isBlank())
                    && (image.getBase64Content() == null || image.getBase64Content().isBlank())) {
                throw new TravelException(Error.IMAGE_UPLOAD_FAILED);
            }

            if (image.getImageUrl() == null || image.getImageUrl().isBlank()) {
                String imageUrl = storageService.uploadPlaceImage(
                        place.getId(),
                        image.getBase64Content(),
                        image.getFileName(),
                        image.getContentType()
                );
                image.setImageUrl(imageUrl);
            }

            image.setPlace(place);
            if (image.getPosition() == null) {
                image.setPosition(index);
            }
            image.setEntityState(EntityState.PERSISTENT);
            place.getImages().add(image);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public Place getPlaceById(UUID id) throws TravelException {
        return placeRepository.findById(id)
                .orElseThrow(() -> new TravelException(Error.PLACE_NOT_FOUND));
    }
}
