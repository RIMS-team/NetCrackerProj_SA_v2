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

    $scope.fetchCardsList();
};