const bodyParser = require("body-parser");
const express = require("express");
const static = express.static(__dirname + '/public');
let app = express();
let configRoutes = require("./routes");
const exphbs = require('express-handlebars');
const cookieParser = require("cookie-parser");

app.engine('handlebars', exphbs({defaultLayout: 'main'}));
app.set('view engine', 'handlebars');
app.use(cookieParser());
app.use("/public", static);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

configRoutes(app);

app.listen(3000, () => {
    console.log("Server online!");
    console.log("Your routes will be running on http://localhost:3000");
    if (process && process.send) process.send({done: true}); // ADD THIS LINE
});