'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notebooks", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("NotebookController", function ($scope, $http, $uibModal) {
        var _this = this;

        $scope.pageSize = 11;

        $scope.fetchNotebookList = function() {
            $http.get('notebook/all').success(function(notebookList){
                $scope.notebook = notebookList;
            });
        };

        $scope.fetchNotebookList();

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }

    });


    app.directive("notebooksList", function () {
        return {
            //restrict: "E",
            templateUrl: "notebook/layout.html"
        }
    });

})();

