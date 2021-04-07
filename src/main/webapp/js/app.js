function getInstanceName() {
    var theUrl = 'rs/instance';
    $.ajax({
        url: theUrl,
        type: 'GET',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            $("#instance_name").text(data.name);
        }
    });
};

function getCode() {
    var theUrl = 'rs/uuid';
    $.ajax({
        url: theUrl,
        type: 'GET',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            $("#uuidcode").text(data.uuid);
        }
    });
};

function getInfo() {
    var theUrl = 'rs/user';
    $.ajax({
        url: theUrl,
        type: 'GET',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            $("#userinfo").text(data);
        }
    });
};

function logOff() {
    var theUrl = 'rs/logout';
    console.log("calling local log off service " + theUrl);
    $.ajax({
        url: theUrl,
        type: 'POST',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            console.log("Log off status: " + data.status);
            window.location = "/index.html";
        }
    });
};

function getUserInfoFromHeaders() {
    var theUrl = 'rs/user';
    $.ajax({
        url: theUrl,
        type: 'GET',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            $("#username").text(data.name);
            $("#email").text(data.email);
            $("#roles").text(data.roles);
            $("#tkn").text(data.accessToken);
            if(data.logins != null){
                $("#logins").text(data.logins);
            }
        }
    });
};
