package by.koroza.zoo_market.service.impl;

import static by.koroza.zoo_market.web.command.name.PagePathName.STORAGE_IMAGES_FOLDER_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.STORAGE_IMAGES_FOLDER_AND_SEPORATOR_PATH;

import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import by.koroza.zoo_market.model.entity.market.product.constituent.ImageFile;
import by.koroza.zoo_market.service.ImageFileService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class ImageFileServiceImpl implements ImageFileService {
	private static final ImageFileService INSTANCE = new ImageFileServiceImpl();

	private ImageFileServiceImpl() {
	}

	public static ImageFileService getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean createFileInFolder(ImageFile imageFile) throws ServiceException {
		boolean result = false;
		try {
			if (imageFile.getName() != null || imageFile.getBytes() != null) {
				File file = new File(STORAGE_IMAGES_FOLDER_PATH);
				if (!file.exists()) {
					file.mkdirs();
					Files.copy(new ByteArrayInputStream(imageFile.getBytes()),
							Path.of(STORAGE_IMAGES_FOLDER_AND_SEPORATOR_PATH + imageFile.getName()),
							StandardCopyOption.REPLACE_EXISTING);
					result = true;
				}
			}
			return result;
		} catch (IOException e) {
			throw new ServiceException(e);
		}
	}
}