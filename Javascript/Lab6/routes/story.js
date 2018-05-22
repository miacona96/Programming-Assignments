const express = require('express');
const router = express.Router();
const story = {
  "storyTitle": "Weird Dream",
  "story": "One time I had a dream that my elementary school was stuck on an asteroid floating through space. Ronald Mcdonald was my teacher and it was very odd \nI also fell off the asteroid and onto and island planet inhabited by teletubbies"
};

router.get("/", (req, res) => {
  try {
        res.json(story);
      } catch(err) {
        res.status(500).send("Server Error:" + err);
      }
});

module.exports = router;