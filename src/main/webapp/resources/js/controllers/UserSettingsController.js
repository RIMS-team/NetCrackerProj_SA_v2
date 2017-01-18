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
                user.password = undefined;
                user.oldEmail = user.eMail;
                $scope.user = user;
            });
        };
        $scope.updateUser = function(user) {
            var updateUser = {id : user.id, fullName : user.fullName, eMail : user.eMail, phoneNumber : user.phoneNumber, password : user.password};
            $http.put('user/update', updateUser).success(function() {
                $scope.getUser();
                $scope.settingsUpdate.$setPristine();
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

