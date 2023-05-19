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