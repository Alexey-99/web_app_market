package by.koroza.zoo_market.service;

import by.koroza.zoo_market.service.exception.ServiceException;

import jakarta.servlet.http.Part;

public interface ImageFileService {

	public String saveImageOnDisk(Part imagePart, String imageUploadDir) throws ServiceException;
}