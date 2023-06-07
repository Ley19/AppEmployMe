const express = require('express');
const router = express.Router();

router.get('/user/:id_user', function (req, res, next) {
    // Requerir el controlador aquí dentro de la función de enrutamiento
    const userController = require('../controller/userController');
    userController.getUserById(req, res, next);
});

router.post('/login', function (req, res, next) {
    console.log("Login")
    // Requerir el controlador aquí dentro de la función de enrutamiento
    const userController = require('../controller/userController');
    userController.login(req, res, next);
});

router.post('/userCreate', function (req, res, next){
    console.log("Nuevo Usuario")
    const userController = require('../controller/userController');
    userController.createUser(req, res, next);
});

module.exports = router;