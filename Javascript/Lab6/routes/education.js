const express = require('express');
const router = express.Router();
const education = [
    {
      "schoolName": "Stevens Institute of Technology",
      "degree": "Computer Science",
      "favoriteClass": "CS 555",
      "favoriteMemory": "First Snowstorm on Campus"
    },
    {
      "schoolName": "Nutley High School",
      "degree": "High School Diploma",
      "favoriteClass": "AP Government",
      "favoriteMemory": "Getting accepted into Stevens"
    },
    {
      "schoolName": "Saint Thomas the Apsotle",
      "degree": "Middle School Diploma",
      "favoriteClass": "World History",
      "favoriteMemory": "Making the starting lineup for the basketball team"
    }
];

router.get("/", (req, res) => {
  try {
        res.json(education);
      } catch(err) {
        res.status(500).send("Server Error:" + err);
      }
});

module.exports = router;