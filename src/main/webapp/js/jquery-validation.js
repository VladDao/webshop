const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const PASSWORD_REG_EXP = (/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]{8,})$/);
const PHONE_REG_EXP = (/^(?=.*[0-9])([0-9]{9,})$/);
const form = $("#registration-form");
function addValidationClasses(field, isValid) {
    field.toggleClass('is-valid', isValid);
    field.toggleClass('is-invalid', !isValid);
}

const checkers = {
    validateStringLength: string => Boolean(string.length),
    validateByRegExp: (regExp, value) => regExp.test(value),
    validateMatches: (val1, val2) => Boolean(val1 === val2)
};

form.submit(function (event) {
    addValidationClasses($('#user-login'), checkers.validateStringLength(this.userLogin.value));
    addValidationClasses($('#user-name'), checkers.validateStringLength(this.userName.value));

    addValidationClasses($('#user-email'), checkers.validateByRegExp(EMAIL_REG_EXP, this.userEmail.value));
    addValidationClasses($('#user-phone'), checkers.validateByRegExp(PHONE_REG_EXP, this.userPhone.value));

    addValidationClasses($('#user-password'), checkers.validateByRegExp(PASSWORD_REG_EXP, this.userPassword.value));
    addValidationClasses($('#user-password2'), checkers.validateMatches(this.userPassword.value, this.userPassword2.value));

    if ($('.is-invalid').length !== 0) {
        event.preventDefault();
    }
});

