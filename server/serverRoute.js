var express = require('express'),
    fs = require('fs'),
    path = require('path');

var formidable = require('formidable');
var FCM = require('fcm-node');

var serverKey = "AIzaSyA1HHzVNt0bISKAFHRZe76vy01YB4nkTkw"; //You will find this in your firebase console
//To get server key Select your project > Click the Gear icon(Settings) > Move to 'Cloud Messaging' tab .
//Under Project credentials your will get your so called long 'Server Key'
var fcm = new FCM(serverKey);
module.exports = function(app) {
    app.get('/sendFcmNotification', function(req, res) {
        var data = req.body;
        var message = "Hey! you got this notification.";
        var title = "DigitSTORY Notification";
        var token = "<Your Device Token for Android>";
        var message = {
            to: token,
            notification: {
                title: title, //title of notification 
                body: message, //content of the notification
                sound: "default",
                icon: "ic_launcher" //default notification icon
            },
            data: data //payload you want to send with your notification
        };
  /*      fcm.send(message, function(err, response) {
            if (err) {
                console.log("Notification not sent");
                res.json({
                    success: false
                })
            } else {
                console.log("Successfully sent with response: ", response);
                res.json({
                    success: true
                })
            }
        });*/
        res.json({
            success: true
        });
    });
}