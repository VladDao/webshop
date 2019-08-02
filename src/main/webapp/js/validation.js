const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const PASSWORD_REG_EXP = (/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,})$/);
const PHONE_REG_EXP = (/^(?=.*[0-9])([0-9]{9,})$/);
const CAPTCHA_LENGTH = 8;

const regForm = document.getElementById('registration-form');

function addValidationClasses(field, isValid) {
    field.classList.toggle('is-valid', isValid);
    field.classList.toggle('is-invalid', !isValid);
}

const checkers = {
    validateStringLength: string => Boolean(string.length),
    validateByRegExp: (regExp, value) => regExp.test(value),
    validateMatches: (val1, val2) => Boolean(val1 === val2),
    validateCaptcha: string => Boolean(string.length === CAPTCHA_LENGTH)
};

function validateStringLength(target) {
    addValidationClasses(target, checkers.validateStringLength(target.value));
}

function validateCaptcha(target) {
    addValidationClasses(target, checkers.validateCaptcha(target.value));
}

function machPassword(event) {
    addValidationClasses(event, checkers.validateMatches(event.value, regForm.elements.userPassword.value));
}

function validateEmail(email) {
    addValidationClasses(email, checkers.validateByRegExp(EMAIL_REG_EXP, email.value));
}

function validatePhone(phone) {
    addValidationClasses(phone, checkers.validateByRegExp(PHONE_REG_EXP, phone.value));
}

function validatePassword(password) {
    addValidationClasses(password, checkers.validateByRegExp(PASSWORD_REG_EXP, password.value));
}

/*Handlers*/
function handleLoginBlur(event) {
    validateStringLength(event.target);
}

function handleUserNameBlur(event) {
    validateStringLength(event.target);
}

function handlePhoneBlur(event) {
    validatePhone(event.target);
}

function handleEmailBlur(email) {
    validateEmail(email.target);
}

function handlePasswordBlur(event) {
    validatePassword(event.target);
}

function handlePasswordBlur2(event) {
    machPassword(event.target);
}

function handleUserCaptcha(event) {
    validateCaptcha(event.target);
}

function validateRegForm(target) {
    const regFormInputs = target.querySelectorAll(':scope input');
    const regFormInputArray = Array.prototype.slice.call(regFormInputs);
    const userLoginInput = regForm.elements.userLogin;
    const userNameInput = regForm.elements.userName;
    const userPhone = regForm.elements.userPhone;
    const userEmail = regForm.elements.userEmail;
    const userPassword = regForm.elements.userPassword;
    const userPassword2 = regForm.elements.userPassword2;
    const userCaptcha = regForm.elements.userCaptcha;

    validateStringLength(userLoginInput);
    validateStringLength(userNameInput);
    validateStringLength(userPhone);
    validateEmail(userEmail);
    validateStringLength(userPassword);
    validateStringLength(userPassword2);
    validateCaptcha(userCaptcha);
    return !regFormInputArray.some(input => input.classList.contains('is-invalid'));
}

function handleRegFormSubmit(event) {
    const isValid = validateRegForm(event.target);

    if (!isValid) {
        event.preventDefault();
    }

}

function initializeRegFormValidation(regForm) {
    const userLoginInput = regForm.elements.userLogin;
    const userNameInput = regForm.elements.userName;
    const userPhone = regForm.elements.userPhone;
    const userEmail = regForm.elements.userEmail;
    const userPassword = regForm.elements.userPassword;
    const userPassword2 = regForm.elements.userPassword2;
    const userCaptcha = regForm.elements.userCaptcha;

    regForm.addEventListener('submit', handleRegFormSubmit);
    userLoginInput.addEventListener('blur', handleLoginBlur);
    userNameInput.addEventListener('blur', handleUserNameBlur);
    userPhone.addEventListener('blur', handlePhoneBlur);
    userEmail.addEventListener('blur', handleEmailBlur);
    userPassword.addEventListener('blur', handlePasswordBlur);
    userPassword2.addEventListener('blur', handlePasswordBlur2);
    userCaptcha.addEventListener('blur', handleUserCaptcha);

}

initializeRegFormValidation(regForm);