'use strict';

/**
 * AccessCardController
 * @constructor
 */
var AccessCardController = function($scope, $http) {
    $scope.fetchCardsList = function() {
        $http.get('accesscards/accesscardlist.json').success(function(cardList){
            $scope.cards = cardList;
        });
    };

    $scope.addNewCard = function(card) {
        console.log(card);
        $http.post('accesscards/add', card).success(function() {
            $scope.fetchCardsList();
            $scope.card.id = '';
            $scope.card.statusName = '';
            $scope.card.inventoryNum = '';
        }).error(function() {
            console.log("error!");
        });
    };

    $scope.updateCard = function(card) {
        console.log(card);
        $http.post('accesscards/update', card).success(function() {
            $scope.fetchCardsList();
            $scope.card.id = '';
            $scope.card.statusName = '';
            $scope.card.inventoryNum = '';
        }).error(function() {
            console.log("error!");
        });
    };

    $scope.removeCard = function(id) {
        $http.delete('accesscards/remove/' + id).success(function() {
            $scope.fetchCardsList();
            $scope.card.id = '';
            $scope.card.statusName = '';
            $scope.card.inventoryNum = '';
        });
    };

    $scope.fetchCardsList();
};