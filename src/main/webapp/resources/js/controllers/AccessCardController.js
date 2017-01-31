'use strict';

/**
 * AccessCardController
 * @constructor
 */

(function () {
    var modul = angular.module("accesscards", ["ngSanitize",
            "angularUtils.directives.dirPagination","ui.bootstrap", "invstatuses"]);

    modul.controller("AccessCardController", function ($scope, $http, $uibModal, $window ,invStatusService, getByIdService) {
        var _this = this;

        $scope.editRecord = {};
        $scope.invStatuses = [];
        $scope.pageSize=5;
        $scope.names = [5,10,25,50,100];
        $scope.selectedName=5;

        $scope.fetchCardsList = function () {
            $http.get('accesscards/accesscardlist.json').success(function (cardList) {
                $scope.cards = cardList;
            });
        };

        $scope.printCards = function (filteredCards) {
            console.log(filteredCards);
            $http.post("accesscards/sendPrintList", filteredCards).success(function () {
                $window.open("accesscards/DownloadPDF","_blank");
            });
        };

        $scope.addNewCard = function (editRec) {
            editRec.statusId = editRec.selectedStatus.id;
            console.log(editRec);
            $http.post('accesscards/add', editRec).success(function (error) {
                $scope.fetchCardsList();
                if(error.id_num != 0) {
                    alert(error.error_m);
                }
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.updateCard = function (editRec) {
            editRec.statusId = editRec.selectedStatus.id;
            $http.post('accesscards/update', editRec).success(function (error) {
                $scope.fetchCardsList();
                if(error.id_num != 0) {
                    alert(error.error_m);
                }
            }).error(function () {
                console.log("Error sending update request!");
            });
        };

        $scope.removeCard = function (id) {
            $http.delete('accesscards/remove/' + id).success(function (error) {
                $scope.fetchCardsList();
                $scope.editRecord.id = '';
                $scope.editRecord.statusId = '';
                $scope.editRecord.statusName = '';
                $scope.editRecord.inventoryNum = '';
                if(error.id_num != 0) {
                    alert(error.error_m);
                }
            });
        };


        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse = !$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }

        $scope.fetchCardsList();



        _this.openEditor = function (card) {
            var editRec = {};
            if (card) {
                editRec.id = card.id;
                editRec.statusId = card.statusId;
                editRec.inventoryNum = card.inventoryNum;
                editRec.selectedStatus = getByIdService.getById(card.statusId, $scope.invStatuses);
            }

            var uibModalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'updateCard.html',
                controller: 'AccessCardEditController'
                ,resolve: {
                    invStatuses: function () {
                        return $scope.invStatuses;
                    },
                     editRecord: function () {
                         return editRec;
                     }
                 }
            });

            uibModalInstance.result.then(function (editRec) {
                //modal ok
                if (card) {
                    $scope.updateCard(editRec);
                }
                else {
                    $scope.addNewCard(editRec);
                }
            }, function () {
                // modal cancel
            });
        };


        $scope.openUpdateEditor = function (card) {
            _this.openEditor(card);
        };

        $scope.openInsertEditor = function () {
            _this.openEditor(null);
        };


        invStatusService.loadList()
            .success(function(InvStatusList){
                $scope.invStatuses = invStatusService.getList();
            }).error(function () {
            $scope.invStatuses = invStatusService.getList();
        });

    });


    modul.controller('AccessCardEditController', ['$scope','$uibModalInstance', 'editRecord', 'invStatuses', function ($scope, uibModalInstance, editRec, invStatuses) {
        $scope.editRecord = editRec;
        $scope.invStatuses = invStatuses;

        $scope.ok = function () {
            // if (validation)
            uibModalInstance.close($scope.editRecord);
            // else
            //   show error msg
        };

        $scope.close = function () {
            uibModalInstance.dismiss('cancel');
        };
    }]);


    modul.directive("accesscardsList", function () {
        return {
            templateUrl: "accesscards/layout.html"
        }
    });

}) ();
