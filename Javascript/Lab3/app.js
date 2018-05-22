//Michael Iacona
//2/11/18
const fs = require('fs');
const { getFileAsString,
		getFileAsJSON,
		saveStringToFile,
		saveJSONToFile } = require('./fileData');

const { createMetrics, simplify } = require('./textMetrics');

async function run(){

	let file1 = 'chapter1.txt';
	let file2 = 'chapter2.txt';
	let file3 = 'chapter3.txt';
	let JSON1 = 'chapter1.result.json';
	let JSON2 = 'chapter2.result.json';
	let JSON3 = 'chapter3.result.json';

	if ( !await fs.existsSync(JSON1) ) {	
		let debug = 'chapter1.debug.txt';
		let words = simplify( await getFileAsString(file1) ).toString();
		await saveStringToFile( debug,words );
		await saveJSONToFile( JSON1, createMetrics(words) );
	}
	console.log(await getFileAsString(JSON1));

	if ( !await fs.existsSync(JSON2) ) {	
		let debug = 'chapter2.debug.txt';
		let words = simplify( await getFileAsString(file2) ).toString();
		await saveStringToFile( debug,words );
		await saveJSONToFile( JSON2, createMetrics(words) );
	}
	console.log(await getFileAsString(JSON2));

	if ( !await fs.existsSync(JSON1) ) {	
		let debug = 'chapter3.debug.txt';
		let words = simplify( await getFileAsString(file3) ).toString();
		await saveStringToFile( debug,words );
		await saveJSONToFile( JSON3, createMetrics(words) );
	}
	console.log(await getFileAsString(JSON3));
}
console.log(run());