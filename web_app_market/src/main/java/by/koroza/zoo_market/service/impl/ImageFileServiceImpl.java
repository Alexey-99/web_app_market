package by.koroza.zoo_market.service.impl;

import static by.koroza.zoo_market.web.command.name.PagePathName.STORAGE_IMAGES_FOLDER_PATH;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.service.ImageFileService;
import by.koroza.zoo_market.service.exception.ServiceException;

import jakarta.servlet.http.Part;

public class ImageFileServiceImpl implements ImageFileService {
	private static final Logger log = LogManager.getLogger();
	private static final ImageFileService INSTANCE = new ImageFileServiceImpl();

	private ImageFileServiceImpl() {
	}

	public static ImageFileService getInstance() {
		return INSTANCE;
	}

	@Override
	public String saveImageOnDisk(Part imagePart, String imageUploadDir) throws ServiceException {
		String imagePath = null;
		try (InputStream inputStream = imagePart.getInputStream()) {
			String submittedName = imagePart.getSubmittedFileName();
			File path = new File(STORAGE_IMAGES_FOLDER_PATH);
			if (!path.exists()) {
				path.mkdirs();
			}
			imagePath = STORAGE_IMAGES_FOLDER_PATH.concat(File.separator).concat(submittedName);
			Files.copy(inputStream, Path.of(imagePath), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.log(Level.ERROR, "exception in method saveImageToDisk()", e);
			throw new ServiceException("Exception when save image", e);
		}
		return imagePath;
	}
}