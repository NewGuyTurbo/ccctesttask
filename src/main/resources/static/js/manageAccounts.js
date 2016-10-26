$(document).ready(function() {
    loadUsers()

    $('#submitAccount').click(editAccount)
})

function loadUsers() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/users",
        dataType: 'json',
        success: function(response) {
            var users = response._embedded.users
            users.forEach(function(user, idx) {
                $('#users').append('<a class="userhref" href="#" userlink="' + user._links.self.href  +'">' + user.name + '</a>').append("  ")
            })
            $('.userhref').click(loadUser)
        },
        error: function(response) {
            console.log("error while loading users")
        }
    })
}

function loadUser() {
    var url = this.attributes.userlink.value
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        dataType: 'json',
        success: function(response) {
            var user = response
            $('#name').val(user.name)
            $('#address').val(user.address)
            $('#password').val(user.password)
            $('#href').val(url)
        },
        error: function(response) {
            console.log("error while loading user")
        }
    })
}

function editAccount() {
    var account = constructAccount()
    var errors = validateAccount(account)
    if (errors && errors.length) alert(errors)
    else saveAccount(account)
}

function saveAccount(account) {
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: $('#href').val(),
        data: JSON.stringify(account),
        dataType: 'json',
        success: function(response) {
            $('#messageBox').text("Account successfully saved")
            $('.userhref').remove()
            loadUsers()
        },
        error: function(response) {
            var message = "There are some troubles.\n\n"
            if (response.responseJSON && response.responseJSON.errorMessages && response.responseJSON.errorMessages.length)
                message += response.responseJSON.errorMessages.join("\n")
            $('#messageBox').text(message)
        }
    })
}

