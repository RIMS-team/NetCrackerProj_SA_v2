'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notebooks", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("NotebookController", function ($scope, $http, $uibModal, $window, invStatusService) {
        var _this = this;

        $scope.invStatuses = [];

        //$scope.pageSize = 5;
        $scope.names = [5,10,25,50,100];
        $scope.selectedName=10;

        $scope.fetchNotebookList = function() {
            $http.get('notebook/all').success(function(notebookList){
                $scope.notebook = notebookList;
            });
        };

        invStatusService.loadList()
            .success(function(){
                $scope.invStatuses = invStatusService.getList();
            }).error(function () {
                $scope.invStatuses = invStatusService.getList();
        });

        $scope.printNotebooks = function (filteredNotebook) {
            $http.post("notebook/sendPrintList", filteredNotebook).success(function () {
                $window.open("notebook/DownloadPDF","_blank");
            });
        };

        $scope.fetchNotebookList();

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        };

        $scope.startsWith = function (actual, expected) {
            var lowerStr = (actual + "").toLowerCase();
            return lowerStr.indexOf(expected.toLowerCase()) === 0;
        };

    });


    app.directive("notebooksList", function () {
        return {
            //restrict: "E",
            templateUrl: "notebook/layout.html"
        }
    });

})();

