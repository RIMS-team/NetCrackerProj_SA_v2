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

    $scope.fetchUserList();
};