package by.koroza.zoo_market.service;

import java.io.InputStream;

import by.koroza.zoo_market.service.exception.ServiceException;

/**
 * The Interface ImageFileService.
 */
public interface ImageFileService {

	/**
	 * Save image on disk.
	 *
	 * @param inputStream   the input stream
	 * @param submittedName the submitted name
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	public String saveImageOnDisk(InputStream inputStream, String submittedName) throws ServiceException;

	/**
	 * Remove the product image from disk.
	 *
	 * @param imagePath the image path
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean removeProductImage(String imagePath) throws ServiceException;
}