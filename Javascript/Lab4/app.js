// Miachael Iacona
// 2/19/2018
// Lab 4
const todoItems = require("./todo");
const connection = require("./mongoConnection");

async function main() {
	
	const t1 = await todoItems.createTask(
		"Ponder Dinosaurs", "Has Anyone Really Been Far Even as Decided to Use Even Go Want to do Look More Like?"
		);
	console.log(t1);
	const t2 = await todoItems.createTask(
		"Play Pokemon with Twitch TV", "Should we revive Helix?"
		);

	let Tasks = await todoItems.getAllTasks();
	console.log(Tasks);
	await todoItems.removeTask(t1._id);

	Tasks = await todoItems.getAllTasks();
	console.log(Tasks);
	await todoItems.completeTask(t2._id);

	let newTask = await todoItems.getTask(t2._id);
	console.log(newTask);

	const db = await connection();
	await db.serverConfig.close();
	console.log("Done!");

}

main().catch(error =>{
	console.error(error);
});