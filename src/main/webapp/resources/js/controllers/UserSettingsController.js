/**
 * Created by Admin on 17.01.2017.
 */


'use strict';

/**
 * Created by trvler135 on 04.01.2017.
 */

(function () {
    var app = angular.module("usersettings", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("UserSettingController", function ($scope, $http) {

        $scope.getUser = function() {
            $http.get('userSetting/getAuthorizedUser').success(function(user) {
                $scope.user = user;
            });
        };
        $scope.updateUser = function(user) {
            console.log(user);
            $http.put('user/update', user).success(function() {
                $scope.getUser();
                $scope.user.fullName = '';
                $scope.user.eMail = '';
                $scope.user.phoneNumber = '';
                $scope.user.password = '';
            }).error(function() {
                console.log("Error sending update request");
            });
        };


        $scope.getUser();
    });


    app.directive("userSetting", function () {
        return {
            templateUrl: "userSetting/layout.html"
        }
    });

})();

