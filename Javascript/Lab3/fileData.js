//Michael Iacona
//2/11/18

const fs = require('fs');

async function getFileAsString(path) {
  return new Promise( 
    function(resolve, reject) {
      if (path == null) {
        return reject("Error: No input given");
      }
      if (typeof(path) != 'string') {
        return reject( "Error: Input must be a string" )
      }
      fs.readFile(path, 'utf-8', function(err, data) {
        if (err) {
          return reject( "Error: cannot read file" );
        }
        return resolve(data);
      });
    });
}

async function getFileAsJSON(path) {
  return new Promise( 
    function(resolve, reject) {
      if ( typeof(path) != 'string' ) {
        return reject("Error: input must be a string")
      }
      getFileAsString(path).then(
        function(result) {
          return resolve( JSON.parse(JSON.stringify(result)) );
      });
    });
}

async function saveStringToFile(path, text) {
  return new Promise( 
    function(resolve, reject) {
      if ( (path == null) || (text == null) ) {
        return reject("Error: please provide an input");
      }
      if (typeof(path) != 'string' || typeof(text) != 'string') {
        return reject( "Error: path and text must be of type string" )
      }

      fs.writeFile(path, text, 'utf-8', function(err) {
        if (err) {
          return reject( "Error: cannot write the file" );
        }
        return resolve(true);
      });
    });
}

async function saveJSONToFile(path, obj) {
  return new Promise( 
    function(resolve, reject) {
      if (typeof(path) != 'string') {
        return reject( "Error: path must be a stirng" )
      }
      if (typeof(obj) != 'object') {
        return reject( "Error: obj must be of type object" )
      }
      saveStringToFile( path, JSON.stringify(obj) ).then(
        function(result) {
          return resolve(true);});
    });
}

module.exports = {
  getFileAsString,
  getFileAsJSON,
  saveStringToFile,
  saveJSONToFile
};