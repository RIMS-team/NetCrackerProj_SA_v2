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

        $scope.fetchCardsList = function () {
            $http.get('accesscards/accesscardlist.json').success(function (cardList) {
                $scope.cards = cardList;
            });
        };

        $scope.addNewCard = function (card) {
            card.statusId = $scope.selectedStatus.id;
            console.log(card);
            $http.post('accesscards/add', card).success(function () {
                $scope.fetchCardsList();
                $scope.card.id = '';
                $scope.card.statusId = '';
                $scope.card.statusName = '';
                $scope.card.inventoryNum = '';
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.updateCard = function (card) {
            card.statusId = card.selectedStatus.id;
            console.log(card);
            $http.post('accesscards/update', card).success(function () {
                $scope.fetchCardsList();
                $scope.card.id = '';
                $scope.card.statusId = '';
                $scope.card.statusName = '';
                $scope.card.inventoryNum = '';
            }).error(function () {
                console.log("Error sending update request!");
            });
        };

        $scope.removeCard = function (id) {
            $http.delete('accesscards/remove/' + id).success(function () {
                $scope.fetchCardsList();
                $scope.card.id = '';
                $scope.card.statusId = '';
                $scope.card.statusName = '';
                $scope.card.inventoryNum = '';
            });
        };

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.fetchCardsList();


        // editor

        $scope.selectedStatus = {};
        $scope.invStatuses = [];

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
