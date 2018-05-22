const express = require('express');
const router = express.Router();

const about = {
  "name": "Michael Iacona",
  "cwid": "10407612",
  "biography": "My name is Michael Iacona and I am a junior at Steven's majoring in computer science.  I spend most of my time either working out, reading, gaming, or hanging out with my girlfriend. I also spend a lot of time training or playing with my new Boston Terrier puppy who is now 9 months old and extremely hyper. I recently began learning how to cook and would like to pick up guitar again after stopping many years ago. My biggest aspiration is to become a good programmer and to find a job after school because debt is scary \n And this is the second paragraph about me",
  "favoriteShows": ["Game of Thrones", "Peaky Blinders", "Black Mirror", "Breaking Bad", "True Detective"],
  "hobbies": ["Weightlifting", "Video Games", "Reading", "Podcasts", "Programming"]
};

router.get("/", (req, res) => {
  try {
        res.json(about);
      } catch(err) {
        res.status(500).send('Server Error:' + err);
      }
});

module.exports = router;