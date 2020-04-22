"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const es6Renderer = require('express-es6-template-engine');
const path = __importStar(require("path"));
var bodyParser = require('body-parser');
const controller_1 = require("./generates/controller");
const service_1 = require("./generates/service");
const repository_1 = require("./generates/repository");
const dto_1 = require("./generates/dto");
const app = express_1.default();
const port = 3000;
app.set('views', path.join(__dirname, './templates'));
app.engine('jt', es6Renderer);
app.set('view engine', 'jt');
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true }));
app.get('/', (req, res) => {
    // console.log(restVariables);
    res.send("Apple Pie");
});
app.post('/controller', (req, res) => {
    // console.log(req.body);
    let inputArg = req.body;
    // console.log(restVariables);
    const restVariables = controller_1.generateControllerVariables(inputArg);
    res.contentType('application/text');
    res.render('rest-controller.jt', { locals: restVariables });
});
app.post('/service', (req, res) => {
    // console.log(req.body);
    let inputArg = req.body;
    // console.log(restVariables);
    const restVariables = service_1.processService(inputArg);
    res.contentType('application/text');
    res.render('service.jt', { locals: restVariables });
});
app.post('/repository', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    // console.log(req.body);
    let inputArg = req.body;
    // console.log();
    const restVariables = yield repository_1.processRepository(inputArg);
    res.contentType('application/text');
    res.render('repository.jt', { locals: restVariables });
}));
app.post('/dto', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    // console.log(req.body);
    let inputArg = req.body;
    // console.log();
    const restVariables = yield dto_1.processDto(inputArg);
    res.contentType('application/text');
    res.render('dto.jt', { locals: restVariables });
}));
app.listen(port, err => {
    if (err) {
        return console.error(err);
    }
    return console.log(`server is listening on ${port}`);
});
//# sourceMappingURL=app.js.map