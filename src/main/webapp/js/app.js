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
    var theUrl = '/simple/rs/logout';
    console.log("calling local log off service " + theUrl);
    $.ajax({
        url: theUrl,
        type: 'POST',
        data: {},
        dataType: 'json',
        complete: function(response, status, xhr){
            var data = jQuery.parseJSON(response.responseText);
            console.log("Log off status: " + data.status);
            console.log("Now calling GLOBAL log off service.");
            globalLogOff();
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
    console.log("===> getting user info from http headers")
};
