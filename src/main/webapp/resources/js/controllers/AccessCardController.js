'use strict';

/**
 * AccessCardController
 * @constructor
 */

(function () {
    var app = angular.module("accesscards", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("AccessCardController", function ($scope, $http, $modal) {
        var _this = this;

        $scope.fetchCardsList = function () {
            $http.get('accesscards/accesscardlist.json').success(function (cardList) {
                $scope.cards = cardList;
            });
        };

        $scope.addNewCard = function (card) {
            console.log(card);
            $http.post('accesscards/add', card).success(function () {
                $scope.fetchCardsList();
                $scope.card.id = '';
                $scope.card.statusName = '';
                $scope.card.inventoryNum = '';
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.updateCard = function (card) {
            console.log(card);
            $http.post('accesscards/update', card).success(function () {
                $scope.fetchCardsList();
                $scope.card.id = '';
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
                $scope.card.statusName = '';
                $scope.card.inventoryNum = '';
            });
        };

        $scope.fetchCardsList();
    })

    app.directive("accesscardsList", function () {
        return {
            templateUrl: "accesscards/layout.html"
        }
    });

}) ();
