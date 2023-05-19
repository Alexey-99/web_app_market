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
	const element = selectElement('.general_infomation_form');
	removeClass(element, 'd-none');
	addClass(element, 'd-flex');
}

function closeGeneralInfomationForm() {
	const element = selectElement('.general_infomation_form');
	removeClass(element, 'd-flex');
	addClass(element, 'd-none');
}

function showLoginPasswordForm() {
	const element = selectElement('.login_password_form');
	removeClass(element, 'd-none');
	addClass(element, 'd-flex');
}

function closeLoginPasswordForm() {
	const element = selectElement('.login_password_form');
	removeClass(element, 'd-flex');
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