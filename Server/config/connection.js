const mysql = require('mysql');

const connection = mysql.createConnection({
    host: 'sql10.freesqldatabase.com',
    database: 'sql10623942',
    user: 'sql10623942',
    password: 'fyZxNI3TZ4'
});

connection.connect(function(err){
    if(err){
        console.log('Error of connection' + err.stack);
        return;
    }
    console.log('Connected with id: ' + connection.threadId);
})

module.exports = connection;

// connection.end();