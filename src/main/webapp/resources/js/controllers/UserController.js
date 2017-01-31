'use strict';

/**
 * Created by trvler135 on 04.01.2017.
 */

(function () {
    var app = angular.module("users", ["ngSanitize", "angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("UserController", function ($scope, $http) {

        $scope.pageSize=5;
        $scope.names = [5,10,25,50,100];
        $scope.selectedName=5;

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
            $http.post('user/add', user).success(function(error) {
                $scope.fetchUserList();
                $scope.user.fullName = '';
                $scope.user.eMail = '';
                $scope.user.phoneNumber = '';
                $scope.user.password = '';
                if(error.id_num != 0){
                    alert(error.error_m);
                }
            }).error(function() {
                console.log("Error sending insert request");
            });
        };

        $scope.updateUser = function(user) {
            console.log(user);
            $http.put('user/update', user).success(function(error) {
                $scope.fetchUserList();
                $scope.user.fullName = '';
                $scope.user.eMail = '';
                $scope.user.phoneNumber = '';
                $scope.user.password = '';
                if(error.id_num != 0){
                    alert(error.error_m);
                }
            }).error(function() {
                console.log("Error sending update request");
            });
        };

        $scope.removeUser = function(id) {
            $http.delete('user/remove/' + id).success(function(error) {
                $scope.fetchUserList();
                if(error.id_num != 0){
                    alert(error.error_m);
                }
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

