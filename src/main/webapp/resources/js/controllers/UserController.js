'use strict';

/**
 * Created by trvler135 on 04.01.2017.
 */

(function () {
    var app = angular.module("users", ["ngSanitize", "angularUtils.directives.dirPagination", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("UserController", function ($scope, $http) {

        $scope.submitAddUserForm = function(user) {
            var sendUser = {fullName : user.fullName, eMail : user.eMail, phoneNumber : user.phoneNumber, password : user.password};
            $scope.addNewUser(sendUser);
            $('#addUser').modal('hide');
            $scope.addUserForm.$setPristine();
            $scope.addUserForm.$setValidity();
        };

        $scope.submitUpdateUserForm = function (user) {
            var updateUser = {id : user.id, fullName : user.fullName, eMail : user.eMail, phoneNumber : user.phoneNumber, password : user.password};
            $scope.updateUser(updateUser);
            $('#updateUser').modal('hide');
            $scope.updateUserForm.$setPristine();
            $scope.updateUserForm.$setValidity();
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

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.fetchUserList();
    });

    app.directive("usersList", function () {
        return {
            templateUrl: "user/layout.html"
        }
    });

})();

