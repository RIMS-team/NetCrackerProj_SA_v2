'use strict';

/**
 * EmpController
 * @constructor
 */


(function () {
    var app = angular.module("employees", ["ngSanitize",
                "angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("EmpController", function ($scope, $http, $uibModal,ordNotifyService) {
        var _this = this;
        $scope.pageSize=5;
        $scope.names = [5,10,25,50,100];
        $scope.selectedName=5;

        // $scope.loadNotifiTempList();
        //
        // $scope.loadNotifiTempList = function () {
        //     var res = $http.get('notification/allDefTemp');
        //     res.success(function(notifiTempList){
        //         $scope.names = notifiTempList;
        //     }).error(function () {
        //         console.log("Error geting orders");
        //     });
        // };

        $scope.fetchEmpsList = function () {
            $http.get('employees/empoyeelist.json').success(function (empList) {
                $scope.emps = empList;
            });
        };
        $scope.sendEmail = function (email) {
            $http.post('mail/send/',email).success(function () {});
        };

        $scope.removeEmp = function (id) {
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

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }


        $scope.fetchEmpsList();



    _this.openEditor = function (templ) {
        var editRec={}
        var settings1={}
        settings1.notif_num=1;
        settings1.user_id=0;
        settings1.template='';
        var settings2={}
        settings2.notif_num=2;
        settings2.user_id=0;
        settings2.template='';
        var settings3={}
        settings3.notif_num=3;
        settings3.user_id=0;
        settings3.template='';
        ordNotifyService.findNotifiTemp1(settings1).success(function () {
            editRec.temp_1='Dear '+templ.fullName+' '+ordNotifyService.getNotifi();
        });
        ordNotifyService.findNotifiTemp1(settings2).success(function () {
            editRec.id='Dear '+templ.fullName+' '+ordNotifyService.getNotifi();
        });
        ordNotifyService.findNotifiTemp1(settings3).success(function () {
            editRec.num='Dear '+templ.fullName+' '+ordNotifyService.getNotifi();
        });
        ordNotifyService.findNotifiTemp1(settings1).success(function () {
            editRec.template='Dear '+templ.fullName+' '+ordNotifyService.getNotifi();
        });
        $scope.editRecord=editRec;


        editRec.email=templ.eMail;
        var uibModalInstance = $uibModal.open({
            animation: true,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'updateNotifTemplate.html',
            controller: 'updateNotifTemplateController',
            resolve: {
                editRecord: function () {
                    return editRec;
                }
            }
        });

        uibModalInstance.result.then(function (editRec) {
            //modal ok
            if (editRec) {
                $scope.sendEmail(editRec);
            }
            else {
                $scope.sendEmail(editRec);
                // TODO: Call insert function()
            }
        }, function () {
            // modal cancel
        });
    };


    $scope.openUpdateEditor = function (templ) {
        _this.openEditor(templ);
    };
    });

    app.controller('updateNotifTemplateController', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
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

    app.directive("employeesList", function () {
        return {
            templateUrl: "employees/layout.html"
        }
    });

})();