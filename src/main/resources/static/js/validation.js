var NAME_ANTI_PATTERN = /[^a-zA-Zа-яА-Я]+/
var ADDRESS_ANTI_PATTERN = /[^a-zA-Zа-яА-Я0-9\s]+/
var PASSWORD_ANTI_PATTERN = /[^a-zA-Zа-яА-Я0-9]+/

function validateAccount(account) {
    var errors = []
    if (account.name && NAME_ANTI_PATTERN.test(account.name))
        errors.push("Name can only consist of letters\n")
    if (account.address && ADDRESS_ANTI_PATTERN.test(account.address))
        errors.push("Address can only consist of letters and numbers\n")
    if (account.password && PASSWORD_ANTI_PATTERN.test(account.password))
        errors.push("Address can only consist of letters and numbers\n")

    return errors
}