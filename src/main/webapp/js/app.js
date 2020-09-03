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
    var theUrl = '/simple/rs/uuid';
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
            window.location = "/simple/index.html";
        }
    });
};

function globalLogOff() {
    var GLO_url = 'http://sso.leo:8080/auth/realms/lc_poc/protocol/openid-connect/logout?redirect_uri=http%3A%2F%2Fsp1.leo%3A8080%2Fsimple';
    $.ajax({
        url: GLO_url,
        type: 'GET',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            console.log("GLO response: " + data);
            window.location = "/simple/index.html";
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
            console.log("username: " + data.name);
            console.log("email: " + data.email);
            console.log("roles: " + data.roles);
            $("#username").text(data.name);
            $("#email").text(data.email);
            $("#roles").text(data.roles);
        }
    });
};
