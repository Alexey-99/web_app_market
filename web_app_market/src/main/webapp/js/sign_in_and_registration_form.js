function closeSignInAndRegistrationForm() {
	const element = selectElement('.sign_in_and_registration_form');
	addClass(element, 'd-none');
}

function showSignInAndRegistrationForm() {
	const element = selectElement('.sign_in_and_registration_form');
	removeClass(element, 'd-none');
}

function openSignInForm() {
	const elementFormTopBtnSignIn = selectElement(
		'.sign_in_and_registration_form_top_btn_sign_in'
	);
	const elementFormTopBtnRegistration = selectElement(
		'.sign_in_and_registration_form_top_btn_registration'
	);
	const elementSignInForm = selectElement('.sign_in_form');
	const elementRegistrationForm = selectElement('.registration_form');
	changeActiveBtn(elementFormTopBtnRegistration, elementFormTopBtnSignIn);
	closeRegistrationForm(elementRegistrationForm, elementSignInForm);
}

function openRegistrationForm() {
	const elementFormTopBtnSignIn = selectElement(
		'.sign_in_and_registration_form_top_btn_sign_in'
	);
	const elementFormTopBtnRegistration = selectElement(
		'.sign_in_and_registration_form_top_btn_registration'
	);
	const elementSignInForm = selectElement('.sign_in_form');
	const elementRegistrationForm = selectElement('.registration_form');
	changeActiveBtn(elementFormTopBtnSignIn, elementFormTopBtnRegistration);
	closeSignInForm(elementSignInForm, elementRegistrationForm);
}

function closeSignInForm(elementSignInForm, elementRegistrationForm) {
	addClass(elementSignInForm, 'd-none');
	removeClass(elementRegistrationForm, 'd-none');
}

function closeRegistrationForm(elementRegistrationForm, elementSignInForm) {
	addClass(elementRegistrationForm, 'd-none');
	removeClass(elementSignInForm, 'd-none');
}

function changeActiveBtn(btnrRemoveActive, btnAddActive) {
	removeClass(btnrRemoveActive, 'active');
	addClass(btnAddActive, 'active');
}

function showPasswordSignInFormInput() {
	const elementBtnSvgOpen = selectElement('.sign_in_form_open_eye');
	const elementBtnSvgClose = selectElement('.sign_in_form_close_eye');
	const elementInputPassword = selectElement('.sign_in_form_input_password');
	const elementBtn = selectElement('.sign_in_form_password_btn');
	changeTypeInputTo(elementInputPassword, 'type', 'text');
	changeOnclickFunctionMethod(elementBtn, 'closePasswordSignInFormInput()');
	changeSvgEye(elementBtnSvgOpen, elementBtnSvgClose);
}

function closePasswordSignInFormInput() {
	const elementBtnSvgOpen = selectElement('.sign_in_form_open_eye');
	const elementBtnSvgClose = selectElement('.sign_in_form_close_eye');
	const elementInputPassword = selectElement('.sign_in_form_input_password');
	const elementBtn = selectElement('.sign_in_form_password_btn');
	changeTypeInputTo(elementInputPassword, 'type', 'password');
	changeOnclickFunctionMethod(elementBtn, 'showPasswordSignInFormInput()');
	changeSvgEye(elementBtnSvgClose, elementBtnSvgOpen);
}

function showPasswordRegistrationFormInput() {
	const elementBtnSvgOpen = selectElement('.registration_form_open_eye');
	const elementBtnSvgClose = selectElement('.registration_form_close_eye');
	const elementInputPassword = selectElement(
		'.registration_form_input_password'
	);
	const elementBtn = selectElement('.registration_form_password_btn');
	changeTypeInputTo(elementInputPassword, 'type', 'text');
	changeOnclickFunctionMethod(
		elementBtn,
		'closePasswordRegistrationFormInput()'
	);
	changeSvgEye(elementBtnSvgOpen, elementBtnSvgClose);
}

function closePasswordRegistrationFormInput() {
	const elementBtnSvgOpen = selectElement('.registration_form_open_eye');
	const elementBtnSvgClose = selectElement('.registration_form_close_eye');
	const elementInputPassword = selectElement(
		'.registration_form_input_password'
	);
	const elementBtn = selectElement('.registration_form_password_btn');
	changeTypeInputTo(elementInputPassword, 'type', 'password');
	changeOnclickFunctionMethod(
		elementBtn,
		'showPasswordRegistrationFormInput()'
	);
	changeSvgEye(elementBtnSvgClose, elementBtnSvgOpen);
}

function changeTypeInputTo(elementInput, nameTypeInput, valueTypeInput) {
	setAttribute(elementInput, nameTypeInput, valueTypeInput);
}

function changeOnclickFunctionMethod(element, onclickValue) {
	setAttribute(element, 'onclick', onclickValue);
}

function changeSvgEye(elementRemoveSvgEye, elementOpenSvgEye) {
	addClass(elementRemoveSvgEye, 'd-none');
	removeClass(elementOpenSvgEye, 'd-none');
}
