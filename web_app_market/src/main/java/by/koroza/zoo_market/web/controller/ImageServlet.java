package by.koroza.zoo_market.web.controller;

import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_PRODUCT_IMAGE;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_PART;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_IS_CORRECT_FILE;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_UPLOAD_FILE_EXCEPTION;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_UPLOAD_FILE_DIRECTORY;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/imageServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 25)
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger();
	private static final String UPLOAD_DIR = "images";
	private static final String MIME_TYPE_IMAGE = "image/";
	private static final String MAIN_SERVLET = "/Controller";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(PARAMETER_COMMAND);
		HttpSession session = request.getSession();
		Part part = null;
		if (commandName != null) {
			part = request.getPart(PARAMETER_PRODUCT_IMAGE);
			processPart(request, response, part);
		} else {
			logger.log(Level.WARN, "The user is trying to upload a file size that is too large");
			session.setAttribute(ATTRIBUTE_UPLOAD_FILE_EXCEPTION, "You try to upload a file size that is too large");
			response.sendRedirect(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
		}
	}

	private void processPart(HttpServletRequest request, HttpServletResponse response, Part part)
			throws ServletException, IOException {
		logger.log(Level.INFO, "method processPart()");
		if (part != null) {
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.isBlank()) {
				String mimeType = getServletContext().getMimeType(fileName);
				if (mimeType.startsWith(MIME_TYPE_IMAGE)) {
					String uploadFileDir = defineUploadFileDirectory(request);
					request.setAttribute(PARAMETER_IS_CORRECT_FILE, true);
					request.setAttribute(PARAMETER_PART, part);
					request.getInputStream();
					request.setAttribute(ATTRIBUTE_UPLOAD_FILE_DIRECTORY, uploadFileDir);
					request.getRequestDispatcher(MAIN_SERVLET).forward(request, response);
				} else {
					logger.log(Level.WARN, "user is trying to upload a file that has other type");
					request.setAttribute(PARAMETER_IS_CORRECT_FILE, false);
					request.getRequestDispatcher(MAIN_SERVLET).forward(request, response);
				}
			} else {
				logger.log(Level.WARN, "file name is empty");
				request.setAttribute(PARAMETER_IS_CORRECT_FILE, true);
				request.getRequestDispatcher(MAIN_SERVLET).forward(request, response);
			}
		} else {
			request.setAttribute(PARAMETER_PART, part);
			request.getRequestDispatcher(MAIN_SERVLET).forward(request, response);
		}
	}

	private String defineUploadFileDirectory(HttpServletRequest request) {
		logger.log(Level.INFO, "method defineUploadFileDirectory()");
		String applicationDir = request.getServletContext().getRealPath("");
		String uploadFileDir = applicationDir.concat(UPLOAD_DIR).concat(File.separator);
		File fileSaveDir = new File(uploadFileDir);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		return uploadFileDir;
	}
}