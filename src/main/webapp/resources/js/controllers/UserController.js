'use strict';

/**
 * Created by trvler135 on 04.01.2017.
 */

(function () {
    var app = angular.module("users", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("UserController", function ($scope, $http) {

        $scope.submitAddUserForm = function(user) {
            console.log(user);
            alert("submit function");
        };

        $scope.fetchUserList = function() {
            $http.get('user/all').success(function(userList){
                $scope.users = userList;
            });
        };

        $scope.addNewUser = function(user) {
            $('#addUser').find('input').val('');
            $http.post('user/add', user).success(function() {
                $scope.fetchUserList();
                $scope.user.fullName = '';
                $scope.user.eMail = '';
                $scope.user.phoneNumber = '';
                $scope.user.password = '';
            }).error(function() {
                console.log("Error sending insert request");
            });
        };

        $scope.updateUser = function(user) {
            console.log(user);
            $http.put('user/update', user).success(function() {
                $scope.fetchUserList();
                $scope.user.fullName = '';
                $scope.user.eMail = '';
                $scope.user.phoneNumber = '';
                $scope.user.password = '';
            }).error(function() {
                console.log("Error sending update request");
            });
        };

        $scope.removeUser = function(id) {
            $http.delete('user/remove/' + id).success(function() {
                $scope.fetchUserList();
            });
        };

        $scope.fetchUserList();
    });

    app.directive("usersList", function () {
        return {
            templateUrl: "user/layout.html"
        }
    });

})();

