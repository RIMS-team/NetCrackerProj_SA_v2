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
        console.log(user);
        $http.post('user/add', user).success(function() {
            $scope.fetchUserList();
        }).error(function() {
            console.log("error!");
        });
    };

    $scope.removeUser = function(id) {
        $http.delete('user/remove/' + id).success(function() {
            $scope.fetchUserList();
        });
    };

    $scope.fetchUserList();
};