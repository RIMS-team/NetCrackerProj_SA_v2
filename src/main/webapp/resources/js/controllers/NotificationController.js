'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notifications", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("NotificationController", function ($scope, $http, $uibModal) {

        $scope.fetchNotificationList = function() {
            $http.get('notification/all').success(function(notificationList){
                $scope.notifications = notificationList;
            });
        };

        $scope.fetchNotificationList();


        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

    });


    app.directive("notificationsList", function () {
        return {
            //restrict: "E",
            templateUrl: "notification/layout.html"
        }
    });

})();


