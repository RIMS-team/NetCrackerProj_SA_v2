'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notebooks", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("NotebookController", function ($scope, $http, $modal) {
        var _this = this;

        // _this.isRowSelected = false;
        // _this.maxSize = 5;
        // _this.totalPages = 1;
        // _this.size = 22;
        // _this.totalElements = 0;
        // _this.currentPage = 1;
        // _this.sort = null;
        // _this.isLast = false;
        // _this.elementsOnPage = 0;
        //
        // $scope.items = [];



        $scope.fetchNotebookList = function() {
            $http.get('notebook/all').success(function(notebookList){
                $scope.notebook = notebookList;
            });
        };

        $scope.fetchNotebookList();


        // $scope.sortChanged = function (grid, sortColumns) {
        //     if (sortColumns.length > 0) {
        //         _this.sort = {name: sortColumns[0].name, dir: sortColumns[0].sort.direction}
        //     } else {
        //         _this.sort = null;
        //     }
        //     _this.currentPage = 1;
        //     _this.refresh();
        // };
        //
        // $scope.gridOptions = {
        //     data: "items",
        //     enableRowSelection: true,
        //     enableRowHeaderSelection: false,
        //     multiSelect: false,
        //     columnDefs: [
        //         {field: "id", displayName: 'ID', visible: false, maxWidth: 140},
        //         {field: "fullName", displayName: "Employee Name", maxWidth: 300},
        //         {field: "phoneNumber", displayName: "Phone Number", maxWidth: 200, enableSorting: false},
        //         {field: "eMail", displayName: "EMail",  maxWidth: 200, enableSorting: false},
        //     ],
        //     modifierKeysToMultiSelect: false,
        //     useExternalSorting: true,
        //     enableGridMenu: true
        // };
        // $scope.gridOptions.onRegisterApi = function (gridApi) {
        //     gridApi.core.on.sortChanged($scope, $scope.sortChanged);
        //     //$scope.sortChanged($scope.gridApi.grid, [$scope.gridOptions.columnDefs[1]]);
        //     gridApi.selection.on.rowSelectionChanged($scope, function (row) {
        //         _this.isRowSelected = row.isSelected;
        //     });
        //     _this.selection = gridApi.selection;
        // };
        //
        //
        // _this.refresh = function () {
        //     // _this.loadList(_this.currentPage, _this.size);
        //     $scope.fetchEmpsList();
        // };


    });


    app.directive("notebooksList", function () {
        return {
            //restrict: "E",
            templateUrl: "notebook/layout.html"
        }
    });

})();

