const mongoCollections = require("../config/mongoCollections");
const recipes = mongoCollections.recipes;
const uuid = require('uuid/v4');

let exportedMethods = {

    getAllRecipes() {
        return recipes().then((recipeCollection) => {
            return recipeCollection.find({}).toArray();
        });
    },

    getRecipeById(id) {
        if (!id){
            throw "Invalid Id";
        }
        return recipes().then((recipeCollection) => {
            return recipeCollection.findOne({ _id: id }).then((recipe) => {
                if (!recipe) throw "Recipe not found";
                return recipe;
            });
        });
    },

    addRecipe(title, ingredients, steps) {
        if (typeof title !== "string") {
            throw "Title must be a string";
        }
        if ( !Array.isArray(ingredients) ){
            throw "Ingredients must be an array";
        }
        if ( !Array.isArray(steps) ){
            throw "Steps must be an array";
        }
        return recipes().then((recipeCollection) => {
            let newRecipe = {
                _id: uuid.v4(),
                title: title,
                ingredients: ingredients,
                steps: steps,
            };

            return recipeCollection
                .insertOne(newRecipe)
                .then((newInsertInformation) => {
                    return newInsertInformation.insertedId;
                })
                .then((newId) => {
                    return this.getRecipeById(newId);
            });
        });
    },

    removeRecipe(id) {
        if (!id) {
            throw "Invalid Id";
        }
        return recipes().then((recipeCollection) => {
            return recipeCollection.removeOne({ _id: id }).then((deletionInfo) => {
                if (deletionInfo.deletedCount === 0) {
                    throw `Could not delete recipe with id of ${id}`;
                }
            });
        });
    },

    updateRecipe(id, title, ingredients, steps) {
        if (!id) {
            throw "Invalid Id";
        }
        if ( typeof title !== 'string' ){
            throw " Title must be a string";
        }
        if ( !Array.isArray(ingredients) ){
            throw "Ingredients must be an array";
        }
        if ( !Array.isArray(steps) ){
            throw "Steps must be an array";
        }
        return recipes().then(recipeCollection => {
            return users.getRecipeById(id).then(recipe => {
                let updatedRecipe = {
                    title: title,
                    ingredients: ingredients,
                    steps: steps
                };
                return recipeCollection
                .updateOne({ _id: id }, updatedRecipe)
                .then(result => {
                    return this.getRecipeById(id);
          });
      });
    });
  }
}

module.exports = exportedMethods;