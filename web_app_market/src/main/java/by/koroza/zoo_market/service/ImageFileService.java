package by.koroza.zoo_market.service;

import by.koroza.zoo_market.model.entity.market.product.constituent.ImageFile;
import by.koroza.zoo_market.service.exception.ServiceException;

public interface ImageFileService {

	public boolean createFileInFolder(ImageFile file) throws ServiceException;

}