/* 9. Система Интернет-магазин. 
 * Администратор осуществляет ведение каталога Товаров. 
 * Клиент делает и оплачивает Заказ на Товары. 
 * Администратор может занести неплательщиков в «черный список». 
 * */
package by.koroza.zoo_market.main;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.OrderDaoImpl;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.reserved.User;
import by.koroza.zoo_market.service.sender.EmailSender;
import by.koroza.zoo_market.web.command.impl.change.ChangePersonInformationCommand;

public class Main {
	private static final Logger log = LogManager.getLogger();

	public static void main(String[] args) throws DaoException {
		String[] arr = { "AA", "BB" };
		EmailSender.getInstance().emailSend("VERIFICATION PERSONAL ACCOUNT",
				"Your code for verification account: " + "123", "koroza.alexey99@gmail.com");

	}
}