/**
 * Created by barmin on 12.01.2017.
 */
'use strict';

/**
 * InvStatusController
 * @constructor
 */

(function () {
    var modul = angular.module("invstatuses", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    modul.controller("InvStatusController", function ($scope, $http, $uibModal, invStatusService) {
        var _this = this;

        invStatusService.loadList()
            .success(function(InvStatusList){
                $scope.invStats = invStatusService.getList();
            }).error(function () {
            $scope.invStats = invStatusService.getList();
        });

        $scope.fetchInvStatusList = function() {
            $http.get('invstats/invstatlist.json').success(function(InvStatusList){
                $scope.invStats = InvStatusList;
            });
        };

        invStatusService.loadList()
            .success(function(InvStatusList){
                $scope.invStats = invStatusService.getList();
            }).error(function () {
            $scope.invStats = invStatusService.getList();
        });

        $scope.addNewStatus = function (editRec) {
            $http.post('invstats/add', editRec).success(function () {
                $scope.fetchInvStatusList();
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.updateStatus = function (editRec) {
            $http.put('invstats/update', editRec).success(function () {
                $scope.fetchInvStatusList();
            }).error(function () {
                console.log("Error sending update request!");
            });
        };



        _this.openEditor = function (status) {
            var editRec = {};
            if (status) {
                editRec.id = status.id;
                editRec.code = status.code;
                editRec.name = status.name;
            }

            var uibModalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'updateInvStat.html',
                controller: 'UpdateInvStatusController',
                resolve: {
                    editRecord: function () {
                        return editRec;
                    }
                }
            });

            uibModalInstance.result.then(function (editRec) {
                //modal ok
                if (status) {
                    $scope.updateStatus(editRec);
                }
                else {
                    $scope.addNewStatus(editRec);
                }
            }, function () {
                // modal cancel
            });
        };

        $scope.openUpdateEditor = function (status) {
            _this.openEditor(status);
        };

        $scope.openInsertEditor = function () {
            _this.openEditor(null);
        };

        modul.directive("invstatusesList", function () {
            return {
                templateUrl: "invstats/layout.html"
            }
        });

        // $scope.fetchInvStatusList();
    });

    modul.directive("invstatusesList", function () {
        return {
            templateUrl: "invstats/layout.html"
        }
    });

    modul.controller('UpdateInvStatusController', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
        $scope.editRecord = editRec;

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

})();


