/**
 * Created by Stas on 29.01.2017.
 */
'use strict';
(function () {
    var app = angular.module("mailsettings", ["ngSanitize", "ui.bootstrap"]);

    app.controller("MailSettingsController", function ($scope, $http) {

        $scope.success = false;

        $scope.getMailSettings = function() {
            $http.get('mailsettings/getMailSettings').success(function(mailSettings) {
                $scope.mailSettings = mailSettings;
                console.log(mailSettings);
            });
        };

        $scope.updateMailSettings = function(mailSettings) {
            var updateMailSettings = {host : mailSettings.host, socketFactoryPort : mailSettings.socketFactoryPort, socketFactoryClass : mailSettings.socketFactoryClass, auth : mailSettings.auth, port : mailSettings.port, from : mailSettings.from,password: mailSettings.password};
            $http.put('mailsettings/updateMailSettings', updateMailSettings).success(function() {
                $scope.getMailSettings();
                $scope.success = true;
            }).error(function() {
                console.log("Error sending update request");
                $scope.success = false;
            });
        };

        $scope.getMailSettings();
    });


    app.directive("mailSettings", function () {
        return {
            templateUrl: "mailsettings/layout.html"
        }
    });

})();
