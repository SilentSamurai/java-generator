import express from 'express';


import es6Renderer from 'express-es6-template-engine';
import * as path from "path";

import bodyParser from 'body-parser';

import {generateControllerVariables} from './generates/controller';
import {processService} from "./generates/service";
import {processRepository} from "./generates/repository";
import {processDto} from "./generates/dto";

const app = express();
const port = 3000;


app.set('views', path.join(__dirname, './templates'));

app.engine('jt', es6Renderer);
app.set('view engine', 'jt');

app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({extended: true}));


app.get('/', (req, res) => {
    // console.log(restVariables);
    res.send("Apple Pie");
});

app.post('/controller', (req, res) => {
    // console.log(req.body);
    const inputArg = req.body;
    // console.log(restVariables);
    const restVariables = generateControllerVariables(inputArg);
    res.contentType('application/text');
    res.render('rest-controller.jt', {locals: restVariables});
});

app.post('/service', (req, res) => {
    // console.log(req.body);
    const inputArg = req.body;
    // console.log(restVariables);
    const restVariables = processService(inputArg);
    res.contentType('application/text');
    res.render('service.jt', {locals: restVariables});
});

app.post('/repository', async (req, res) => {
    // console.log(req.body);
    const inputArg = req.body;
    // console.log();
    const restVariables = await processRepository(inputArg);
    res.contentType('application/text');
    res.render('repository.jt', {locals: restVariables});
});

app.post('/dto', async (req, res) => {
    // console.log(req.body);
    const inputArg = req.body;
    // console.log();
    const restVariables = await processDto(inputArg);
    res.contentType('application/text');
    res.render('dto.jt', {locals: restVariables});
});


app.listen(port, err => {
    if (err) {
        // tslint:disable-next-line:no-console
        return console.error(err);
    }
    // tslint:disable-next-line:no-console
    return console.log(`server is listening on ${port}`);
});