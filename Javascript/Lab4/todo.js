// Miachael Iacona
// 2/19/2018
// Lab 4
const mongoCollections = require("./mongoCollections");
const todo = mongoCollections.todoItems;
const uuid = require("uuid/v4");

module.exports = {
  
  async createTask(title,description) {
    const collection = await todo( "todoItems" );
    
    if ( !title ) {
      throw "you must input a title";
    }
    if ( typeof(title) != "string" ) {
      throw "invalid title";
    }
    if ( !description ) {
      throw "you must input a description";
    }
    if ( typeof(description) != "string" ) {
      throw "invalid description";
    }

    const Task = {
      _id: uuid(),
      title,
      description,
      completed: false,
      completedAt: null
    };

    const addTask = await collection.insertOne( Task );
    if ( addTask.insertedCount == 0 ) {
      throw  "could not add new task" ;
    }
    const find = await collection.findOne(
      {_id: Task._id}
      );
    return find;
  },

  async getAllTasks() {
    const collection = await todo( "todoItems" );
    const find = await collection.find().toArray();
    return find;
  },

  async getTask(id) {
    const collection = await todo( "todoItems" );

    if ( !id ) {
      throw "invalid id";
    }
    if ( typeof(id) != "string" ) {
      throw "invalid id";
    }
    
    const next = await collection.findOne({ _id: id});
    
    if ( !next ) {
      throw "next task not found";
    }
    return next;
  },

  async completeTask(taskId) {
    const collection = await todo( "todoItems" );

    if( taskId == undefined){
      throw "invalid id";
    }
    if ( typeof(taskId) != "string" ) {
      throw "invalid id";
    }

    const next = await collection.replaceOne(
      {_id: taskId },
      {$set: {
        complete: true,
        completedAt: new Date()
      }
    });

    if ( next.modifiedCount == 0 ) {
      throw "Could not update the task";
    }
    return next.value;
  },

  async removeTask(id) {
    const collection = await todo( "todoItems" );

    if( id == undefined ){
      throw "invalid id";
    }
    if( typeof(id) != "string" ) {
      throw "invalid id" ;
    }

    const next = await collection.removeOne({ _id: id});

    if ( next.deletedCount == 0 ) {
      throw "unable to find task";
    }

    return next;
  }
}




