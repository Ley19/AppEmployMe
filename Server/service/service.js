const db = require('../config/connection');



function getUserById(id, callback) {
  console.log(id);
  const query = 'SELECT * FROM user WHERE id_user = ?';
  db.query(query, [id], callback);
}

function login(email, password, callback) {
  const query = 'SELECT * FROM user WHERE email = ? AND password = ?';
  db.query(query, [email, password], (error, results) => {
    if (error) {
      console.error('Error al realizar el inicio de sesión: ', error);
      callback(error, null);
    } else if (results.length === 0) {
      
      callback(false, null);
    } else {
      const user = {
        id: results[0].id,
        name: results[0].name,
        email: results[0].email
      };
      callback(null, user);
    }
  });
}

module.exports = {
  getUserById,
  login,
};
