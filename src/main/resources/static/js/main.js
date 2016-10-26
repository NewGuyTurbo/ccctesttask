function constructAccount() {
    var account = {}
    account.name = $('#name').val()
    account.address = $('#address').val()
    account.password = $('#password').val()
    return account
}