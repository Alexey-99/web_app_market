function addProductPet(id) {
	let i = localStorage.length + 1
	let key = 'p = ' + i;
	if (isHaveKeyInLocalStorage(key) == false) {
		localStorage.setItem(key, id);
	} else {
		while (isHaveKeyInLocalStorage(key)) {
			key = 'p = ' + ++i;
		}
		localStorage.setItem(key, id);
	}
}

function addProductOtherProducts(id) {
	let i = localStorage.length + 1
	let key = 'o = ' + i;
	if (isHaveKeyInLocalStorage(key) == false) {
		localStorage.setItem(key, id);
	} else {
		while (isHaveKeyInLocalStorage(key)) {
			key = 'o = ' + ++i;
		}
		localStorage.setItem(key, id);
	}
}

function showToast(arrayProductsId) {
	for (let id of arrayProductsId) {
		console.log("productId = " + id)
		let toastTrigger = document.getElementById("liveToastBtn" + id);
		let toastLiveExample = document.getElementById("liveToast" + id);
		if (toastTrigger) {
			const toastBootstrap =
				bootstrap.Toast.getOrCreateInstance(toastLiveExample);
			toastTrigger.addEventListener('click', () => {
				toastBootstrap.show();
			});
		}
	}
}

function isHaveKeyInLocalStorage(key) {
	let result = false;
	const lengthLocakStorage = localStorage.length;
	for (let i = 0; i < lengthLocakStorage; i++) {
		if (localStorage.key(i) == key) {
			result = true
		}
	}
	return result;
}