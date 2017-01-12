'use strict';

/**
 * Created by trvler135 on 04.01.2017.
 */
var UserController = function($scope, $http) {

    $scope.fetchUserList = function() {
        $http.get('user/all').success(function(userList){
            $scope.users = userList;
        });
    };

    $scope.addNewUser = function(user) {
        $('.user-add-form').find('input').val('');
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
};