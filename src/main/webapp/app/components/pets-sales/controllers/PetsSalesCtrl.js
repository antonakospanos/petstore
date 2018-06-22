(function () {
	"use strict";
	angular
		.module("PetStoreUi")
		.controller("PetsSalesCtrl", ["$rootScope", "$scope", "$http", "$state", PetsSalesCtrl]);

	function PetsSalesCtrl($rootScope, $scope, $http, $state) {
        var ctrl = this;
        ctrl.addFlowUrl = $rootScope.backend_protocol + "://" + $rootScope.backend_ip + ":" + $rootScope.backend_port + "/" + $rootScope.backend_context_path + "/sales";

        ctrl.init = function() {
            $scope.pet = {};
            $scope.initialModel = angular.copy($scope.pet);
        }

        ctrl.reset = function() {
            var message = "This will reset the form. Proceed anyway?";
            $scope.modalWarning(message, "RESET")
                .then(function (response) {
                    if (response === true) {
                        $scope.pet = angular.copy($scope.initialModel);
                        // location.reload();
                        $scope.scrollTop();
                    }
                });
        }

        ctrl.cancel = function() {
            var message = "Your work will be lost. Proceed anyway?";
            $scope.modalWarning(message, "PROCEED")
                .then(function (response) {
                    if (response === true) {
                        $state.go("pets_review");
                        $scope.scrollTop();
                    }
                });
        }

        ctrl.getFormCtrl = function() {
            var retval = $scope.$$childHead
            if (retval) {
                retval = retval.formCtrl;
            }
            return retval;
        }

        ctrl.isValid = function() {
            var formCtrl = ctrl.getFormCtrl();
            if (formCtrl && formCtrl.$valid) {
                return true;
            }

            return false;
        }

        ctrl.add = function() {
            var message = "This will publish '"+$scope.pet.title +"' to the Pet Store. Proceed?";
            var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                },
            }
            $scope.modalWarning(message, "ADD")
                .then(function (response) {
                    if (response === true) {
                        $http.post(ctrl.addFlowUrl, $scope.pet, config)
                            .then(function successCallback(response) {
                                $scope.refreshPets();
                                $state.go("pets_review");
                                // Reload footer's img to switch from alert to check-mark!
                                $scope.createToast(response.data.result + "! " + response.data.description)
                                if ($rootScope.pets === 0) {
                                  location.reload();
                                } else {
                                  $scope.scrollTop();
                                }
                            }, function errorCallback(response) {
                                $scope.createToast(response.data.result + "! " + response.data.description)
                                // var message = response.data.result + "<br/>" + response.data.description;
                                // $scope.modalError(message, "100");
                            });
                    }
                });
        }
    }
}());
