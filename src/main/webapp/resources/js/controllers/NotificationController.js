'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notifications", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("NotificationController", function ($scope, $http, $uibModal, ordNotifyService) {
        var _this = this;

        //ordNotifyService.loadNotifiTempList();

        $scope.pageSize = 11;
        $scope.editRecord;
        $scope.temp_1;
        ordNotifyService.findNotifiTemp1(1).success(function () {
            $scope.temp_1=ordNotifyService.getNotifi();
        });
        $scope.id;
        ordNotifyService.findNotifiTemp1(2).success(function () {
            $scope.id=ordNotifyService.getNotifi();
        });
        $scope.num;
        ordNotifyService.findNotifiTemp1(3).success(function () {
            $scope.num=ordNotifyService.getNotifi();
        });


        ordNotifyService.loadUserList();

        $scope.fetchNotificationList = function() {
            $http.get('notification/all').success(function(notificationList){
                $scope.notifications = notificationList;
            });
        };

        $scope.submitUpdate = function(notifi) {
            $scope.updateNotification(notifi);
        };

        $scope.updateNotification = function(notifi) {
            $http.put('notification/update', notifi).success(function() {
                ordNotifyService.findNotifiTemp1(1).success(function () {
                    $scope.temp_1=ordNotifyService.getNotifi();
                });
                ordNotifyService.findNotifiTemp1(2).success(function () {
                    $scope.id=ordNotifyService.getNotifi();
                });
                ordNotifyService.findNotifiTemp1(3).success(function () {
                    $scope.num=ordNotifyService.getNotifi();
                });
            }).error(function() {
                console.log("Error sending update request");
            });
        };

        $scope.fetchNotificationList();

        $scope.insertNewTemplete = function (editRec) {
            $http.post('notification/insert', editRec).success(function () {
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }

        _this.openEditor = function (templ) {
            var editRec = {};
            var template_1;
            if (templ) {
                editRec.notif_num = templ.notifi_id;
                editRec.user_id = ordNotifyService.getUserIdByOrderId(templ.order.id);

                ordNotifyService.findNotifiTemp1(templ.notifi_id).success(function () {
                    $scope.edit=ordNotifyService.getNotifi();
                });
                editRec.template=$scope.edit;

                //alert(ordNotifyService.getEmployeeIdByOrderId(templ.order.id));
            }

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
                    $scope.insertNewTemplete(editRec);
                }
                else {
                    $scope.insertNewTemplete(editRec);
                    // TODO: Call insert function()
                }
            }, function () {
                // modal cancel
            });
        };

        _this.openEditorDef = function () {
                var editRec={}
                editRec.temp_1=$scope.temp_1;
                editRec.id=$scope.id;
                editRec.num=$scope.num;

            var uibModalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'updateNotifTemplateDef.html',
                controller: 'updateNotifTemplateControllerTemp',
                resolve: {
                    editRecord: function () {
                        return editRec;
                    }
                }
            });

            uibModalInstance.result.then(function (editRec) {
                //modal ok
                    $scope.updateNotification(editRec);
            }, function () {
                // modal cancel
            });
        };

        $scope.openUpdateEditor = function (templ) {
            _this.openEditor(templ);
        };
        $scope.openUpdateEditorDef = function () {
            _this.openEditorDef();
        };

    });


    app.directive("notificationsList", function () {
        return {
            //restrict: "E",
            templateUrl: "notification/layout.html"
        }
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

    app.controller('updateNotifTemplateControllerTemp', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
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


