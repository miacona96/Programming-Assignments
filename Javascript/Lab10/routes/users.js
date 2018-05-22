const express = require("express");
const router = express.Router();
const users = require("../data/users");

router.get("/", async (req, res) => {
    let user = await users.findId(req.cookies.AuthCookie); 
    if (user) {
        res.render("private", {user:user});
        res.redirect("/private");
    } else {
        res.render("login");
    }
});

router.post("/login", async(req, res) => {
    const inputUsername = req.body.username;
    const inputPassword = req.body.password;
    if (!inputUsername || !inputPassword) {
        var err = {
            error : "Error: Please provide a username and password to login",
        }
        res.status(403).render("login",err);
    }
    if(users.findName(inputUsername)){
        const user = await users.validate(inputUsername, inputPassword);
        if(user){
            res.cookie("AuthCookie", users.findName(inputUsername)._id);
            res.redirect("/private");
        }
        else{
            var err = {
                error : "Error: Invalid password provided",
            }
            res.status(403).render("login", err);
        }
    }
    else{
        var err = {
                error : "Error: Invalid username provided",
            }
        res.status(403).render("login", err);
    }
});

router.get("/logout", async (req, res) => {
    res.cookie("AuthCookie", "", {expires: new Date()});
    res.clearCookie("AuthCookie");
    res.render("logout");
});

module.exports = router;