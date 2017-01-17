'use strict';

/**
 * AccessCardController
 * @constructor
 */

(function () {
    var modul = angular.module("accesscards", ["ngSanitize","angularUtils.directives.dirPagination","ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize",
        "invstatuses"]);

    modul.controller("AccessCardController", function ($scope, $http, $modal, invStatusService) {
        var _this = this;

        $scope.editRecord = {};
        //$scope.selectedStatus = {};
        $scope.invStatuses = [];

        $scope.fetchCardsList = function () {
            $http.get('accesscards/accesscardlist.json').success(function (cardList) {
                $scope.cards = cardList;
            });
        };

        $scope.addNewCard = function (editRecord) {
            editRecord.statusId = editRecord.selectedStatus.id;
            console.log(editRecord);
            $http.post('accesscards/add', editRecord).success(function () {
                $scope.fetchCardsList();
                $scope.editRecord.id = '';
                $scope.editRecord.statusId = '';
                $scope.editRecord.statusName = '';
                $scope.editRecord.inventoryNum = '';
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.updateCard = function (editRecord) {
            editRecord.statusId = editRecord.selectedStatus.id;
            console.log(editRecord);
            $http.post('accesscards/update', editRecord).success(function () {
                $scope.fetchCardsList();
                $scope.editRecord.id = '';
                $scope.editRecord.statusId = '';
                $scope.editRecord.statusName = '';
                $scope.editRecord.inventoryNum = '';
            }).error(function () {
                console.log("Error sending update request!");
            });
        };

        $scope.removeCard = function (id) {
            $http.delete('accesscards/remove/' + id).success(function () {
                $scope.fetchCardsList();
                $scope.editRecord.id = '';
                $scope.editRecord.statusId = '';
                $scope.editRecord.statusName = '';
                $scope.editRecord.inventoryNum = '';
            });
        };

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.fetchCardsList();


        // editor

        $scope.openUpdateEditor = function (editRecord) {
            // $modal.open({
            $uibModal.open({
                animation: true,
                templateUrl: 'accesscards/layout.html',
                controller: 'AccessCardController',
                resolve: {
                    // recordId: function () {
                    //     return recordId;
                    // },
                    sender: function () {
                        return _this;
                    }
                }
            });
        }


        invStatusService.loadList()
            .success(function(InvStatusList){
                $scope.invStatuses = invStatusService.getList();
            }).error(function () {
            $scope.invStatuses = invStatusService.getList();
        });


    })

    modul.directive("accesscardsList", function () {
        return {
            //restrict: "E",
            templateUrl: "accesscards/layout.html"
        }
    });

}) ();
