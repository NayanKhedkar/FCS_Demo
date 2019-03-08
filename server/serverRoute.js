var express = require('express'),
    fs = require('fs'),
    path = require('path');

var formidable = require('formidable');
var FCM = require('fcm-node');

var serverKey = "AAAAG586dy8:APA91bF0Raydt8I93_SSWpDhMf5qnN5AXqtvBS5hgGvYl4gWU62KXdoKxiAhKqZjcUY3sZNC4A5uZ2LP_IImV6Qfkfol65bQW1aqfrBOcvmIcX_-uA6nDZt1IBUKz2PBT55BoTeYReKA"; //You will find this in your firebase console
//To get server key Select your project > Click the Gear icon(Settings) > Move to 'Cloud Messaging' tab .
//Under Project credentials your will get your so called long 'Server Key'
const fcm = new FCM(serverKey);
module.exports = function(app) {
    app.post('/sendFcmNotification', function(req, res) {

        let msg = "Hey! you got this notification from Nayan.";
        let title = "TEST Notification";
        if (!req.body.token) {
            res.json({
                success: false,
                msg: "Sorry! Unable to received device Token",
                statusCode: 402
            });
        };

        let token = req.body.token;
        let message = {
            to: token,
            notification: {
                title: title, //title of notification 
                body: msg, //content of the notification
                sound: "default",
                icon: "ic_launcher" //default notification icon
            },
            data: {
               type: '850', //payload you want to send with your notification
               time: '2:45'
            }
        };
        fcm.send(message, function(err, response) {
            if (err) {
                console.log("Notification not sent");
                res.json({
                    success: false,
                    msg:err,
                    statusCode:402
                });
            } else {
                console.log("Successfully sent with response: ", response);
                res.json({
                    success: true,
                    msg:"Notification set successfully.",
                    statusCode:402
                });
            }
        });
    });
}
