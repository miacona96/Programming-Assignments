const routes = require("./results");

const constructorMethod = app => {
    app.use("/", routes);
    app.use("*", (req, res) => {
        res.status(404).json({error: "No route found."});
    });

};

module.exports = constructorMethod;