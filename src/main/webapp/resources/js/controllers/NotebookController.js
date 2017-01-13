'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notebooks", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("NotebookController", function ($scope, $http, $modal) {
        var _this = this;

        $scope.fetchNotebookList = function() {
            $http.get('notebook/all').success(function(notebookList){
                $scope.notebook = notebookList;
            });
        };

        $scope.fetchNotebookList();

    });


    app.directive("notebooksList", function () {
        return {
            templateUrl: "notebook/layout.html"
        }
    });

})();

