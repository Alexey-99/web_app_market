function deleteProductByIdFromLocalStorage(type, id) {
	let flag = true;
	for (let i = 0; i < localStorage.length; i++) {
		const key = localStorage.key(i);
		const value = localStorage.getItem(key);
		if ((flag == true) && (type == key[0] && id == value)) {
			localStorage.removeItem(key);
			flag = false;
		}
	}
}

function deleteAllProductsFromLocalStorage() {
	const localeStorageLength = localStorage.length;
	for (let i = localeStorageLength - 1; i >= 0; i--) {
		const key = localStorage.key(i);
		localStorage.removeItem(key);
		console.log("deleteAllProductsFromLocalStorage")
	}
}

function getProducts(inputClass) {
	let idProducts = '';
	for (let i = 0; i < localStorage.length; i++) {
		const key = localStorage.key(i);
		const value = localStorage.getItem(key);
		idProducts = idProducts + key + ',' + value + ' ; ';
	}
	const inputElement = document.querySelector(inputClass);
	inputElement.setAttribute('value', idProducts);
}

function deleteProducAndGetProducts(inputClass, type, id) {
	deleteProductByIdFromLocalStorage(type, id);
	getProducts(inputClass);
}

function showToast(arrayProductsPetsId, arrayProductsOtherId) {
	for (
		let i = 0;
		i < arrayProductsPetsId.length + arrayProductsOtherId.length;
		i++
	) {
		const toastTrigger = document.getElementById('liveToastBtn' + i);
		const toastLiveExample = document.getElementById('liveToast' + i);
		if (toastTrigger) {
			const toastBootstrap =
				bootstrap.Toast.getOrCreateInstance(toastLiveExample);
			toastTrigger.addEventListener('click', () => {
				toastBootstrap.show();
			});
		}
	}
}

function showPaymentInformationForm() {
	const elementPaymentInformationForm = selectElement(
		".payment_information_form"
	);
	removeClass(elementPaymentInformationForm, "d-none");
}

function closedPaymentInformationForm() {
	const elementPaymentInformationForm = selectElement(
		".payment_information_form"
	);
	addClass(elementPaymentInformationForm, "d-none");
}