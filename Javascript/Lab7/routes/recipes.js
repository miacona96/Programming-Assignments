const express = require("express");
const router = express.Router();
const recipeData = require("../data/recipes");

router.get("/:id", async(req, res) => {
  try {
    let recipe = await recipeData.getRecipeById(req.params.id);
    res.json(recipe); 
  } catch (e) {
    console.log(e);
    res.status(404).json({error: "Recipe not found"});
  }
});

router.get("/", async (req, res) => {
  try {
    const recipeList = await recipeData.getAllRecipes();
    res.json(recipeList);
  } catch (e) {
    console.log(e);
    res.status(500).json({error: e});
  }
});

router.post("/", async(req, res) => {
  const newData = req.body;
  try {
    const {title, ingredients, steps } = newData;
    const newRecipe = await recipeData.addRecipe(title, ingredients, steps);
    res.json(createdRecipe);
  } catch (e) {
    console.log(e);
    res.status(500).json({error: e});
  }
});

router.put("/:id", async (req, res) => {
  const updatedData = req.body;
  try {
    await recipeData.getRecipeById(req.params.id);
  } catch (e) {
    res.status(404).json({ error: "Recipe not found" });
  }
  try {
    const updatedRecipe = await recipeData.updateRecipe(req.params.id, updatedData);
    res.json(updatedRecipe);
  } catch (e) {
    res.status(500).json({ error: e });
  }
});

router.patch("/:id", async (req, res) => {
  const updatedData = req.body;
  try {
    await recipeData.getRecipeById(req.params.id);
  } catch (e) {
    res.status(404).json({ error: "Recipe not found" });
  }
  try {
    const updatedRecipe = await recipeData.updateRecipe(req.params.id, updatedData);
    res.json(updatedRecipe);
  } catch (e) {
    res.status(500).json({ error: e });
  }
});

router.delete("/:id", async (req, res) => {
  try {
    await RecipeData.getRecipeById(req.params.id);
  } catch (e) {
    res.status(404).json({ error: "Recipe not found" });
  }
  try {
    await recipeData.removeRecipe(req.params.id);
  } catch (e) {
    res.status(500).json({ error: e });
  }
});


module.exports = router;