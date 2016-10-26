function createAccount() {
    var account = constructAccount()
    var errors = validateAccount(account)
    if (errors && errors.length) alert(errors)
    else submitAccount(account)
}

function constructAccount() {
    var account = {}
    account.name = $('#name').val()
    account.address = $('#address').val()
    account.password = $('#password').val()
    return account
}

function submitAccount(account) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/users",
        data: JSON.stringify(account),
        dataType: 'json',
        success: function(response) {
            $('#messageBox').text("Account created")
        },
        error: function(response) {
            var message = "There are some troubles.\n\n"
            if (response.responseJSON && response.responseJSON.errorMessages && response.responseJSON.errorMessages.length)
                message += response.responseJSON.errorMessages.join("\n")
            $('#messageBox').text(message)
        }
    })
}

$(document).ready(function() {
    $('#submitAccount').click(function() {
        createAccount()
    })
})