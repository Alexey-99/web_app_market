package by.koroza.zoo_market.web.tag;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.CommandName.COMMAND_SHOW_MAKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.ImagePath.LOGO_PNG_IMAGE_PATH;
import static by.koroza.zoo_market.web.command.name.LanguageName.ENGLISH;
import static by.koroza.zoo_market.web.command.name.LanguageName.RUSSIAN;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_IMAGE_FILE_PATH;
import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.ParameterValue.NUMBER_FIRST_PAGE_VALUE;
import static by.koroza.zoo_market.web.command.name.ServletName.MAIN_SERVLET_CONTROLLER_NAME;
import static by.koroza.zoo_market.web.command.name.ServletName.SERVLET_SHOW_IMAGE_NAME;

import java.io.IOException;
import java.util.List;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

public class ProductFeedsAndOtherPaginationTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private static int numberLastPage;
	private int maxCountProductsOnPage;
	private int numberPage;

	public void setMaxCountProductsOnPage(int maxCountProductsOnPage) {
		if (maxCountProductsOnPage < 1) {
			this.maxCountProductsOnPage = 1;
		} else {
			this.maxCountProductsOnPage = maxCountProductsOnPage;
		}
	}

	public void setNumberPage(int numberPage) {
		if (numberPage < 1) {
			this.numberPage = 1;
		} else {
			this.numberPage = numberPage;
		}
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			HttpSession session = pageContext.getSession();
			String locale = (String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE);
			@SuppressWarnings("unchecked")
			List<FeedAndOther> listProductFeedsAndOther = (List<FeedAndOther>) session
					.getAttribute(ATTRIBUTE_LIST_PRODUCTS_FEEDS_AND_OTHER);
			AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
			printProducts(listProductFeedsAndOther, locale, user);
			printPagination(listProductFeedsAndOther, locale);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	private void printProducts(List<FeedAndOther> listProductFeedsAndOther, String locale, AbstractRegistratedUser user)
			throws IOException {
		StringBuilder builder = new StringBuilder();
		int indexFirstElement = maxCountProductsOnPage * (numberPage - 1);
		builder.append("""
				<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3 row-cols-xxl-4 g-4 mb-4">
				""");
		for (int i = indexFirstElement; i < listProductFeedsAndOther.size()
				&& i < indexFirstElement + maxCountProductsOnPage; i++) {
			FeedAndOther feedAndOther = listProductFeedsAndOther.get(i);
			builder.append("""
					<div class="col card_product">
						<div class="card h-100 card_product_inner">
							<div class="card-img-top card_img" style="border: 1px solid var(--bs-card-border-color)">
					""");
			if (feedAndOther.getImagePath() != null) {
				builder.append("""
						<img class="w-100 h-100" alt="" src="
						""").append(SERVLET_SHOW_IMAGE_NAME).append("?").append(PARAMETER_IMAGE_FILE_PATH).append("=")
						.append(feedAndOther.getImagePath()).append("""
								" />
								""");
			} else {
				builder.append(
						"""
								<svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="100%" height="100%" viewBox="0 0 64 64">
									<path d="M 3 8 C 1.347656 8 0 9.347656 0 11 L 0 53 C 0 54.652344 1.347656 56 3 56 L 61 56 C 62.652344 56 64 54.652344 64 53 L 64 11 C 64 9.347656 62.652344 8 61 8 Z M 3 10 L 61 10 C 61.550781 10 62 10.449219 62 11 L 62 53 C 62 53.550781 61.550781 54 61 54 L 3 54 C 2.449219 54 2 53.550781 2 53 L 2 11 C 2 10.449219 2.449219 10 3 10 Z M 17 14 C 16.398438 14 16 14.445313 16 15 L 16 17 C 16 17.550781 16.398438 18 17 18 C 17.601563 18 18 17.554688 18 17 L 18 15 C 18 14.445313 17.601563 14 17 14 Z M 11.894531 15.335938 C 11.761719 15.351563 11.628906 15.398438 11.5 15.472656 C 10.980469 15.773438 10.859375 16.359375 11.136719 16.839844 L 12.136719 18.570313 C 12.320313 18.894531 12.65625 19.074219 13 19.074219 C 13.132813 19.09375 13.328125 19.039063 13.5 18.9375 C 14.019531 18.636719 14.140625 18.050781 13.863281 17.570313 L 12.863281 15.839844 C 12.660156 15.480469 12.289063 15.285156 11.894531 15.335938 Z M 22.109375 15.339844 C 21.714844 15.285156 21.34375 15.480469 21.136719 15.839844 L 20.136719 17.574219 C 19.859375 18.050781 19.980469 18.636719 20.5 18.9375 C 20.675781 19.039063 20.871094 19.097656 21 19.074219 C 21.347656 19.074219 21.683594 18.890625 21.867188 18.574219 L 22.867188 16.839844 C 23.144531 16.359375 23.023438 15.773438 22.5 15.472656 C 22.371094 15.398438 22.238281 15.355469 22.109375 15.339844 Z M 8.464844 19 C 8.082031 18.945313 7.699219 19.113281 7.472656 19.5 C 7.171875 20.019531 7.363281 20.589844 7.839844 20.867188 L 9.570313 21.867188 C 9.730469 21.960938 9.875 22.042969 10.054688 22.03125 C 10.414063 22.007813 10.738281 21.847656 10.9375 21.5 C 11.238281 20.980469 11.050781 20.410156 10.570313 20.136719 L 8.839844 19.136719 C 8.71875 19.066406 8.59375 19.019531 8.464844 19 Z M 25.53125 19 C 25.40625 19.019531 25.28125 19.066406 25.160156 19.136719 L 23.425781 20.136719 C 22.949219 20.410156 22.761719 20.980469 23.0625 21.5 C 23.261719 21.847656 23.585938 22.007813 23.945313 22.03125 C 24.125 22.042969 24.269531 21.957031 24.425781 21.863281 L 26.160156 20.863281 C 26.640625 20.589844 26.824219 20.019531 26.527344 19.5 C 26.300781 19.109375 25.914063 18.945313 25.53125 19 Z M 17.074219 20.007813 C 14.320313 20.007813 12.082031 22.246094 12.082031 25 C 12.082031 27.753906 14.320313 29.992188 17.074219 29.992188 C 19.828125 29.992188 22.066406 27.753906 22.066406 25 C 22.066406 22.246094 19.828125 20.007813 17.074219 20.007813 Z M 17.074219 21.992188 C 18.738281 21.992188 20.082031 23.335938 20.082031 25 C 20.082031 26.664063 18.738281 28.007813 17.074219 28.007813 C 15.410156 28.007813 14.066406 26.664063 14.066406 25 C 14.066406 23.335938 15.410156 21.992188 17.074219 21.992188 Z M 7 24 C 6.445313 24 6 24.398438 6 25 C 6 25.601563 6.445313 26 7 26 L 9 26 C 9.554688 26 10 25.601563 10 25 C 10 24.398438 9.554688 24 9 24 Z M 25 24 C 24.445313 24 24 24.398438 24 25 C 24 25.601563 24.445313 26 25 26 L 27 26 C 27.554688 26 28 25.601563 28 25 C 28 24.398438 27.554688 24 27 24 Z M 9.945313 28 C 9.816406 28.019531 9.691406 28.066406 9.570313 28.136719 L 7.839844 29.136719 C 7.363281 29.410156 7.171875 29.980469 7.472656 30.5 C 7.671875 30.847656 7.996094 31.007813 8.355469 31.03125 C 8.535156 31.042969 8.679688 30.957031 8.839844 30.863281 L 10.570313 29.863281 C 11.050781 29.589844 11.238281 29.019531 10.9375 28.5 C 10.710938 28.109375 10.324219 27.945313 9.945313 28 Z M 24.054688 28 C 23.671875 27.945313 23.289063 28.113281 23.0625 28.5 C 22.761719 29.019531 22.949219 29.589844 23.425781 29.867188 L 25.160156 30.867188 C 25.320313 30.960938 25.464844 31.042969 25.640625 31.03125 C 26.003906 31.007813 26.324219 30.847656 26.527344 30.5 C 26.824219 29.980469 26.640625 29.410156 26.160156 29.136719 L 24.425781 28.136719 C 24.308594 28.066406 24.183594 28.019531 24.054688 28 Z M 13.109375 30.925781 C 12.714844 30.875 12.339844 31.070313 12.136719 31.429688 L 11.136719 33.160156 C 10.859375 33.640625 10.980469 34.226563 11.5 34.527344 C 11.675781 34.628906 11.871094 34.683594 12 34.660156 C 12.347656 34.660156 12.683594 34.480469 12.867188 34.160156 L 13.867188 32.429688 C 14.144531 31.949219 14.023438 31.363281 13.5 31.0625 C 13.371094 30.988281 13.238281 30.941406 13.109375 30.925781 Z M 20.894531 30.925781 C 20.761719 30.941406 20.628906 30.988281 20.5 31.0625 C 19.980469 31.363281 19.859375 31.949219 20.136719 32.425781 L 21.136719 34.160156 C 21.320313 34.484375 21.65625 34.660156 22 34.660156 C 22.132813 34.683594 22.328125 34.625 22.5 34.527344 C 23.019531 34.226563 23.140625 33.640625 22.863281 33.160156 L 21.863281 31.425781 C 21.660156 31.070313 21.289063 30.875 20.894531 30.925781 Z M 17 32 C 16.398438 32 16 32.445313 16 33 L 16 35 C 16 35.554688 16.398438 36 17 36 C 17.601563 36 18 35.554688 18 35 L 18 33 C 18 32.445313 17.601563 32 17 32 Z M 48 32.859375 C 47.222656 32.859375 46.445313 33.140625 45.878906 33.707031 L 39.492188 40.09375 L 36.097656 36.699219 C 35 35.597656 33 35.597656 31.902344 36.699219 L 18.597656 50 L 13.042969 50 C 12.417969 50 12 50.398438 12 51 C 12 51.601563 12.523438 52 13.042969 52 L 58.980469 52 C 59.5 52 60.023438 51.601563 60.023438 51 C 60.023438 50.398438 59.5 50 58.980469 50 L 32.414063 50 L 47.292969 35.121094 C 47.671875 34.742188 48.328125 34.742188 48.707031 35.121094 L 58.292969 44.707031 C 58.683594 45.097656 59.316406 45.097656 59.707031 44.707031 C 60.097656 44.316406 60.097656 43.683594 59.707031 43.292969 L 50.121094 33.707031 C 49.554688 33.140625 48.777344 32.859375 48 32.859375 Z M 34 37.800781 C 34.25 37.800781 34.5 37.898438 34.699219 38.097656 L 38.09375 41.492188 L 29.585938 50 L 21.402344 50 L 33.300781 38.097656 C 33.5 37.898438 33.75 37.800781 34 37.800781 Z M 5 50 C 4.398438 50 4 50.398438 4 51 C 4 51.601563 4.398438 52 5 52 L 9 52 C 9.601563 52 10 51.601563 10 51 C 10 50.398438 9.601563 50 9 50 Z" />
								</svg>
								""");
			}
			builder.append("""
					</div>
					<div class="card-body d-flex flex-column justify-content-between">
						<div class="description d-flex flex-column justify-content-between h-100">
							<ul class="discription_top">
					""");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("<li> номер товара: o-").append(feedAndOther.getId()).append("</li>");
				builder.append("<li> тип продукта: ").append(feedAndOther.getProductType()).append("</li>");
				builder.append("<li> брэнд: ").append(feedAndOther.getBrand()).append("</li>");
				builder.append("<li> описание товара: ").append(feedAndOther.getDescriptions()).append("</li>");
				builder.append("<li> товар подходит для след. типов питомцев: ").append(feedAndOther.getPetTypes()
						.toString().substring(1, feedAndOther.getPetTypes().toString().length() - 1)).append("</li>");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("<li> number of product: o-").append(feedAndOther.getId()).append("</li>");
				builder.append("<li> type of product: ").append(feedAndOther.getProductType()).append("</li>");
				builder.append("<li> brand of product: ").append(feedAndOther.getBrand()).append("</li>");
				builder.append("<li> description of product: ").append(feedAndOther.getDescriptions()).append("</li>");
				builder.append("<li> this product is suitable for the following types of pets: ").append(feedAndOther
						.getPetTypes().toString().substring(1, feedAndOther.getPetTypes().toString().length() - 1))
						.append("</li>");
			} else {
				builder.append("<li> number of product: o-").append(feedAndOther.getId()).append("</li>");
				builder.append("<li> type of product: ").append(feedAndOther.getProductType()).append("</li>");
				builder.append("<li> brand of product: ").append(feedAndOther.getBrand()).append("</li>");
				builder.append("<li> description of product: ").append(feedAndOther.getDescriptions()).append("</li>");
				builder.append("<li> This product is suitable for the following types of pets: ").append(feedAndOther
						.getPetTypes().toString().substring(1, feedAndOther.getPetTypes().toString().length() - 1))
						.append("</li>");
			}
			builder.append("""
					</ul>
					<ul class="discription_botton">
					""");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("<li> цена: ").append(feedAndOther.getPrice()).append("</li>");
				builder.append("<li> скидка: ").append(feedAndOther.getDiscount())
						.append("""
								<svg class="discription_botton_discont_procent_img" xmlns="http://www.w3.org/2000/svg" height="48" viewBox="0 96 960 960" width="48">
									<path d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z"/>
								</svg>
								</li>
								<li> стоимость со скодкой:
										""")
						.append(" ").append(feedAndOther.getTotalPrice()).append("</li>");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("<li> price: ").append(feedAndOther.getPrice()).append("</li>");
				builder.append("<li> discount: ").append(feedAndOther.getDiscount())
						.append("""
								<svg class="discription_botton_discont_procent_img" xmlns="http://www.w3.org/2000/svg" height="48" viewBox="0 96 960 960" width="48">
									<path d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z"/>
								</svg>
								</li>
								<li> total price:
										""")
						.append(" ").append(feedAndOther.getTotalPrice()).append("</li>");
			} else {
				builder.append("<li> price: ").append(feedAndOther.getPrice()).append("</li>");
				builder.append("<li> discount: ").append(feedAndOther.getDiscount())
						.append("""
								<svg class="discription_botton_discont_procent_img" xmlns="http://www.w3.org/2000/svg" height="48" viewBox="0 96 960 960" width="48">
									<path d="M289.899 516Q236 516 198 477.899t-38-92Q160 332 198.101 294t92-38Q344 256 382 294.101t38 92Q420 440 381.899 478t-92 38Zm-.017-60Q319 456 339.5 435.618q20.5-20.383 20.5-49.5Q360 357 339.618 336.5q-20.383-20.5-49.5-20.5Q261 316 240.5 336.382q-20.5 20.383-20.5 49.5Q220 415 240.382 435.5q20.383 20.5 49.5 20.5Zm380.017 440Q616 896 578 857.899t-38-92Q540 712 578.101 674t92-38Q724 636 762 674.101t38 92Q800 820 761.899 858t-92 38Zm-.017-60Q699 836 719.5 815.618q20.5-20.383 20.5-49.5Q740 737 719.618 716.5q-20.383-20.5-49.5-20.5Q641 696 620.5 716.382q-20.5 20.383-20.5 49.5Q600 795 620.382 815.5q20.383 20.5 49.5 20.5ZM202 896l-42-42 598-598 42 42-598 598Z"/>
								</svg>
								</li>
								<li> total price:
										""")
						.append(" ").append(feedAndOther.getTotalPrice()).append("</li>");
			}
			builder.append("</ul> </div>");
			if (user != null && user.getRole().getIdRole() >= UserRole.USER.getIdRole()
					&& user.isVerificatedEmail() == true) {
				builder.append("""
						<div class="row body_btns w-100">
							<div class="col-12 d-flex justify-content-center body_btn">
								<input type="hidden" id="productId" value="
						""").append(feedAndOther.getId()).append("""
						" />

						""").append("<button class=\"w-100 h-100 body_btn_input\" id=\"liveToastBtn")
						.append(feedAndOther.getId()).append("\" type=\"button\" onclick=\"addProductOtherProducts(")
						.append(feedAndOther.getId()).append(", ").append(i).append(") \">");
				if (locale.equalsIgnoreCase(RUSSIAN)) {
					builder.append("в карзину");
				} else if (locale.equalsIgnoreCase(ENGLISH)) {
					builder.append("in basket");
				} else {
					builder.append("in basket");
				}
				builder.append("""
								</button>
							</div>
						</div>
						""");
			}
			builder.append("""
					</div>
					<div class="card-footer">
						<small class="text-body-secondary">
					""");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("Последнее обновление ").append(feedAndOther.getUpdateDateTime().toStringRus())
						.append(" назад");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("Last update of the product ").append(feedAndOther.getUpdateDateTime().toStringEn())
						.append(" ago");
			} else {
				builder.append("Last update of the product ").append(feedAndOther.getUpdateDateTime().toStringEn())
						.append(" ago");
			}
			builder.append("""
								</small>
							</div>
						</div>
					</div>
					<div class="toast-container position-fixed bottom-0 end-0 p-3">
							""").append("<div id=\"liveToast").append(feedAndOther.getId()).append("""
					" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
					<div class="toast-header">
					""").append("<img class=\"rounded me-2 toast_logo\" src=\"").append(SERVLET_SHOW_IMAGE_NAME)
					.append("?").append(PARAMETER_IMAGE_FILE_PATH).append("=").append(LOGO_PNG_IMAGE_PATH)
					.append("\" alt=\"logo\"").append("/>")
					.append("""
							<strong class="me-auto">
											Zoo ковчег
										</strong>
									<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Закрыть"></button>
									</div>
									<div class="toast-body">
							""");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("Вы добавили товар");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("You have added a product");
			} else {
				builder.append("You have added a product");
			}
			builder.append("""
							</div>
						</div>
					</div>
							""");
		}
		builder.append("</div>");
		pageContext.getOut().write(builder.toString());
	}

	private void printPagination(List<FeedAndOther> listProductfeedsAndOther, String locale) throws IOException {
		StringBuilder builder = new StringBuilder();
		int countPage = (int) Math.ceil((double) listProductfeedsAndOther.size() / this.maxCountProductsOnPage);
		numberLastPage = countPage;
		builder.append("""
				<nav>
					<ul class="pagination d-flex justify-content-center align-items-center">
				""");
		if (this.numberPage > NUMBER_FIRST_PAGE_VALUE) {
			builder.append("""
						<li class="page-item" style="cursor: pointer">
							<a class="page-link" href="
					""").append(MAIN_SERVLET_CONTROLLER_NAME).append("?").append(PARAMETER_COMMAND).append("=")
					.append(COMMAND_SHOW_MAKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE).append("&")
					.append(PARAMETER_NUMBER_PAGE).append("=").append(this.numberPage - 1).append("""
							">
							""");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("Предыдущая");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("Previous");
			} else {
				builder.append("Previous");
			}
			builder.append("""
							</a>
						</li>
					""");
		}
		for (int i = 1; i < countPage + 1; i++) {
			if (i == this.numberPage) {
				builder.append("""
						<li class="page-item active" style="cursor: pointer">
						""");
			} else {
				builder.append("""
						<li class="page-item" style="cursor: pointer">
						""");
			}
			builder.append("""
					<a class="page-link" href="
					""").append(MAIN_SERVLET_CONTROLLER_NAME).append("?").append(PARAMETER_COMMAND).append("=")
					.append(COMMAND_SHOW_MAKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE).append("&")
					.append(PARAMETER_NUMBER_PAGE).append("=").append(i).append("""
							" >
							""");
			builder.append(i);
			builder.append("""
							</a>
						</li>
					""");
		}
		if (this.numberPage < numberLastPage) {
			builder.append("""
					<li class="page-item" style="cursor: pointer">
						<a class="page-link" href="
					""").append(MAIN_SERVLET_CONTROLLER_NAME).append("?").append(PARAMETER_COMMAND).append("=")
					.append(COMMAND_SHOW_MAKET_PAGE_PRODUCT_FEEDS_AND_OTHER_BY_NUMBER_PAGE).append("&")
					.append(PARAMETER_NUMBER_PAGE).append("=").append(this.numberPage + 1).append("""
							" >
							""");
			if (locale.equalsIgnoreCase(RUSSIAN)) {
				builder.append("Следующая");
			} else if (locale.equalsIgnoreCase(ENGLISH)) {
				builder.append("Next");
			} else {
				builder.append("Next");
			}
			builder.append("""
						</a>
					</li>
					""");
		}
		builder.append("""
					</ul>
				</nav>
				""");
		pageContext.getOut().write(builder.toString());
	}
}