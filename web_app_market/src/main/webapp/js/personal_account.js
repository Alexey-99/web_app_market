function showPassword() {
	const elementTextPassword = selectElement('.text_password');
	const elementBtn = selectElement('.password_btn');
	const elementBtnSvgOpen = selectElement('.open_eye');
	const elementBtnSvgClose = selectElement('.close_eye');
	removeClass(elementTextPassword, 'hide_password');
	addClass(elementTextPassword, 'show_password');
	setAttribute(elementBtn, 'onclick', 'closePassword()');
	addClass(elementBtnSvgOpen, 'd-none');
	removeClass(elementBtnSvgClose, 'd-none');
}

function closePassword() {
	const elementTextPassword = selectElement('.text_password');
	const elementBtn = selectElement('.password_btn');
	const elementBtnSvgOpen = selectElement('.open_eye');
	const elementBtnSvgClose = selectElement('.close_eye');
	removeClass(elementTextPassword, 'show_password');
	addClass(elementTextPassword, 'hide_password');
	setAttribute(elementBtn, 'onclick', 'showPassword()');
	addClass(elementBtnSvgClose, 'd-none');
	removeClass(elementBtnSvgOpen, 'd-none');
}

function showGeneralInfomationForm() {
	const element = selectElement('.change_general_infomation_form');
	removeClass(element, 'd-none');
}

function closeGeneralInfomationForm() {
	const element = selectElement('.change_general_infomation_form');
	addClass(element, 'd-none');
}

function showLoginForm() {
	const element = selectElement('.change_login_form');
	removeClass(element, 'd-none');
}

function closeLoginForm() {
	const element = selectElement('.change_login_form');
	addClass(element, 'd-none');
}

function showPasswordForm() {
	const element = selectElement('.change_password_form');
	removeClass(element, 'd-none');
}

function closePasswordForm() {
	const element = selectElement('.change_password_form');
	addClass(element, 'd-none');
}

function showPasswordInLoginPasswordFormInput() {
	const elementBtnSvgOpen = selectElement('.login_password_form_open_eye');
	const elementBtnSvgClose = selectElement('.login_password_form_close_eye');
	const elementInputPassword = selectElement(
		'.login_password_form_input_password'
	);
	const elementBtn = selectElement('.login_password_form_password_btn');
	setAttribute(elementInputPassword, 'type', 'text');
	setAttribute(
		elementBtn,
		'onclick',
		'closePasswordInLoginPasswordFormInput()'
	);
	addClass(elementBtnSvgOpen, 'd-none');
	removeClass(elementBtnSvgClose, 'd-none');
}

function closePasswordInLoginPasswordFormInput() {
	const elementBtnSvgOpen = selectElement('.login_password_form_open_eye');
	const elementBtnSvgClose = selectElement('.login_password_form_close_eye');
	const elementInputPassword = selectElement(
		'.login_password_form_input_password'
	);
	const elementBtn = selectElement('.login_password_form_password_btn');
	setAttribute(elementInputPassword, 'type', 'password');
	setAttribute(elementBtn, 'onclick', 'showPasswordInLoginPasswordFormInput()');
	removeClass(elementBtnSvgOpen, 'd-none');
	addClass(elementBtnSvgClose, 'd-none');
}

function closeLoginPasswordForm() {
	const element = selectElement('.login_password_form');
	removeClass(element, 'd-flex');
	addClass(element, 'd-none');
}

function showAddProductForm() {
	const element = selectElement(".add_product_form");
	removeClass(element, "d-none");
}

function closeAddProductForm() {
	const element = selectElement(".add_product_form");
	addClass(element, "d-none");
}

function openAddProductPetForm() {
	const elementFormTopBtnAddProductPet = selectElement(
		'.add_product_form_top_btn_product_pet'
	);
	const elementFormTopBtnAddOtherProduct = selectElement(
		'.add_product_form_top_btn_other_product'
	);
	const elementAddProductPetForm = selectElement('.add_product_pet_form');
	const elementAddOtherProductForm = selectElement('.add_other_product_form');
	changeActiveBtn(elementFormTopBtnAddOtherProduct, elementFormTopBtnAddProductPet);
	closeAddOtherProductForm(elementAddOtherProductForm, elementAddProductPetForm);
}

function openAddOtherProductForm() {
	const elementFormTopBtnAddProductPet = selectElement(
		'.add_product_form_top_btn_product_pet'
	);
	const elementFormTopBtnAddOtherProduct = selectElement(
		'.add_product_form_top_btn_other_product'
	);
	const elementAddProductPetForm = selectElement('.add_product_pet_form');
	const elementAddOtherProductForm = selectElement('.add_other_product_form');
	changeActiveBtn(elementFormTopBtnAddProductPet, elementFormTopBtnAddOtherProduct);
	closeAddOtherProductForm(elementAddProductPetForm, elementAddOtherProductForm);
}

function closeAddProductPetForm(elementAddClassNone, elementRemoveClassNone) {
	addClass(elementAddClassNone, 'd-none');
	removeClass(elementRemoveClassNone, 'd-none');
}

function closeAddOtherProductForm(elementAddClassNone, elementRemoveClassNone) {
	addClass(elementAddClassNone, 'd-none');
	removeClass(elementRemoveClassNone, 'd-none');
}

function changeActiveBtn(btnrRemoveActive, btnAddActive) {
	removeClass(btnrRemoveActive, 'active');
	addClass(btnAddActive, 'active');
}

function showProductImage(productId) {
	const element = selectElement(`.image_product_id_${productId}`);
	removeClass(element, "d-none");
}

function closedProductImage(productId) {
	const element = selectElement(`.image_product_id_${productId}`);
	addClass(element, "d-none");
}


function showChangeProductForm(productId) {
	const element = selectElement(`.change_product_form_${productId}`);
	removeClass(element, "d-none");
}

function closedChangeProductForm(productId) {
	const element = selectElement(`.change_product_form_${productId}`);
	addClass(element, "d-none");
}

function showChangeUserStatusForm() {
	const element = selectElement('.change_user_status_form');
	removeClass(element, 'd-none');
}

function closedChangeUserStatusForm() {
	const element = selectElement('.change_user_status_form');
	addClass(element, 'd-none');
}