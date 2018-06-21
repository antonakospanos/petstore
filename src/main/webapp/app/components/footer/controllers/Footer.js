/* global angular, _, configData,moment,$,waitingDialog */
(function () {
    "use strict";
    angular
        .module("PetStoreUi")
        .controller("FooterCtrl", ["$rootScope", "$scope", "$http", "$mdToast", FooterCtrl]);

    function FooterCtrl($rootScope, $scope, $http, $mdToast) {

        // Auto refresh footer every 5 seconds to re-calculate backend changes
        // $scope.intervalTimer = setInterval(function () {
        //     $scope.refreshPets()
        // }, 5000);

        $scope.refreshPets = function() {
            refreshPets();
        }

        $scope.initFooter = function initFooter() {
            setProgressBar('none')
            refreshPets();
        }

        function refreshPets() {
            var petsUrl = $rootScope.backend_protocol + "://" + $rootScope.backend_ip + ":" + $rootScope.backend_port + "/" + $rootScope.backend_context_path + "/pets";

            // Lookup for /pets
            $http.get(petsUrl)
                .then(function successCallback(response) {
                    $rootScope.pets = response.data.length;
                    setConfigCheck('display')
                    setConfigAlert('none')
                    setConfigPrompt($rootScope.pets + " pets stored in the Pet Store");
                }, function errorCallback(response) {
                   $rootScope.pets = response.data.length;
                    setConfigCheck('none')
                    setConfigAlert('display')
                    if ($rootScope.pets === undefined) {
                        setConfigPrompt("Could not find pets in the Pet Store");
                    } else {
                        setConfigPrompt($rootScope.pets + " pets stored in the Pet Store");
                    }
                });
        }

        function setConfigCheck(display) {
            var check = document.getElementById("configCheck");
            check.style.display = display;
        }

        function setConfigAlert(display) {
            var alert = document.getElementById("configAlert");
            alert.style.display = display;
        }

        function setConfigPrompt(msg) {
            var divElement = document.getElementById("configPrompt");
            for (var i = 0; i < divElement.childNodes.length; ++i) {
                divElement.removeChild(divElement.childNodes[i]);
            }
            var textNode = document.createTextNode(msg);
            divElement.appendChild(textNode);
        }

        function setProgressBar(display) {
            var actions = document.getElementById("progressBar");
            actions.style.display = display;
        }

        $scope.createToast = function(message) {
            $mdToast.show(
                $mdToast.simple()
                    .textContent(message)
                    .parent(document.querySelectorAll('#toaster'))
                    .hideDelay(5000)
                    .action('x')
                    .capsule(true)
            );
        };
    }
}());
